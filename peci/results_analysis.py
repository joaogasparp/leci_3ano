import os
import textwrap
from statistics import mean, stdev, median

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import seaborn as sns

import json


def get_metric_graph(data, metric, all_classes, compare_dependence):
    metric_graph = metric

    if metric == "fpr":
        if "gesture_fpr" in data:
            metric_graph = "gesture_fpr"
        elif "nogesture_recall" in data:
            metric_graph = "nogesture_fnr"

    if all_classes or compare_dependence:
        if metric == "accuracy":
            metric_graph = "balanced_" + metric
        elif metric != "fpr":
            metric_graph = "weighted_avg_" + metric

    return metric_graph


def get_metric_label(data, metric, gesture=None):
    metric_label = metric
    if gesture is not None:
        metric_label = gesture + "_" + metric

        return metric_label

    if metric == "fpr":
        if "gesture_fpr" in data:
            metric_label = "gesture_fpr"
        elif "nogesture_recall" in data:
            a = [100 - val for val in data["nogesture_recall"]]
            data.insert(data.shape[1], "fpr", a)

    if metric == "accuracy":
        metric_label = "balanced_" + metric
    elif metric != "fpr":
        metric_label = "weighted_" + metric

    return metric_label


def get_title(compare_dependence, user_dependent, all_tests, all_classes, test_name):
    title = ""

    if compare_dependence is not None:
        if not compare_dependence:
            if user_dependent:
                title += "User-dependent"
            elif not user_dependent:
                title += "User-independent"
        else:
            title += "User-dependent vs independent"

        if compare_dependence:
            if all_tests:
                title += ", all subjects"
            if all_classes:
                title += ", all classes"
    else:
        if all_tests:
            title += "All subjects"
        if all_classes:
            if all_tests:
                title += ", all subjects"
            else:
                title += "All subjects"

    if all_tests is None and test_name is not None:
        title += test_name

    return title


def get_filename(metric, compare_dependence, all_users, all_gestures, test_name):
    filename = metric

    if not compare_dependence:
        if all_users is True:
            filename += "_all subjs"

        if all_gestures is True:
            filename += "_all classes"

    if test_name:
        filename += "_" + test_name

    return filename


def get_boxplot(data, class_list, compare_dependence, user_dependent, metric_graph, metric_label, all_tests, test_col_name,
                all_classes, test_name):
    boxplot = None

    title = get_title(compare_dependence, user_dependent, all_tests, all_classes, test_name)

    if compare_dependence is False or compare_dependence is None:

        if user_dependent is not None:
            if user_dependent:
                data = data[data["subj_dependent"] == 'yes']
            else:
                data = data[data["subj_dependent"] == 'no']

        if all_classes is True:
            if all_tests is True or all_tests is None:
                boxplot = sns.boxplot(data=data, y=metric_graph, palette="Set1")
            else:
                boxplot = sns.boxplot(data=data, y=metric_graph, x=test_col_name, palette="Set1")
                boxplot.set_xlabel("Trajectory")
        elif metric_graph == "precision" or metric_graph == "recall" or metric_graph == "f1":
            class_data = []

            for fold in np.unique(data["inner_fold"]):
                fold_data = data[data["inner_fold"] == fold]
                for class_ in class_list:
                    new_line = [class_, fold, fold_data[class_ + "_" + metric_graph]]
                    if len(class_data) == 0:
                        class_data = [new_line]
                    else:
                        class_data = np.concatenate((class_data, [new_line]), axis=0)

            new_data = pd.DataFrame(class_data, columns=["class", "fold", "metric"])
            new_data = new_data.explode("metric")
            new_data["metric"] = new_data["metric"].astype("float")

            if all_tests is True or all_tests is None:
                boxplot = sns.boxplot(data=new_data, y="metric", x="class", palette="Set1")
                boxplot.set_xlabel("Subject")
            else:
                boxplot = sns.boxplot(data=new_data, y="metric", x="subj", hue="gesture", palette="Set1")
                boxplot.set_xlabel("Participant")
                boxplot.legend_.set_title("Gesture")
                boxplot.legend(loc='center left', bbox_to_anchor=(1, 0.5), ncol=1, frameon=False)
    else:
        boxplot = sns.boxplot(data=data, y=metric_graph, x="subj_dependent", palette="Set2", order=["yes", "no"])
        boxplot.set_xticklabels(["User-dependent", "User-independent"])
        boxplot.set_xlabel("User dependence")

    if boxplot:
        boxplot.set_title(title)
        boxplot.set_ylabel(metric_label)

    return boxplot


def plot_results(data, class_list, metric, metric_label, folder, compare_dependence, user_dependent, all_users,
                 all_classes, test_name):
    selected_data = data
    # Select defined classifier only and/or indicated subject
    test_col_name = None
    if not all_users:
        test_col_name = "Test_Type"

    metric_graph = get_metric_graph(selected_data, metric, all_classes, compare_dependence)

    if metric_graph == "nogesture_fnr":
        selected_data["nogesture_fnr"] = 100 - data["nogesture_recall"]

    plt.figure()

    boxplot = get_boxplot(selected_data, class_list, compare_dependence, user_dependent, metric_graph, metric_label, all_users,
                          test_col_name, all_classes, test_name)
    if boxplot is None:
        return

    filename = get_filename(metric, compare_dependence, all_users, all_classes, test_name)

    plt.tight_layout()

    plt.savefig(os.path.join(folder, filename + ".eps"))
    plt.savefig(os.path.join(folder, filename + ".png"))

    plt.close()


def create_plots(data, class_list, results_folder_path, metric_list, compare_dependence, user_dependent, all_users,
                 all_gestures, test_name):
    print("Creating plots...")

    # For each metric
    for metric in metric_list:
        metric_name = metric_list[metric]

        plot_results(data, class_list, metric, metric_name, results_folder_path, compare_dependence, user_dependent,
                     all_users, all_gestures, test_name)


def print_metrics(data, metric_list, gesture=None):
    for metric in metric_list:

        metric_label = get_metric_label(data, metric, gesture)

        if metric_label in data:
            metric_values = data[metric_label]*100
            metric_name = metric_list[metric]

            if len(metric_values) > 1:
                print("\t\t{name:s} = {mean:.1f} +/- {std:.1f} (median: {median:.1f})".format(
                    name=metric_name, mean=mean(metric_values), std=stdev(metric_values), median=median(metric_values)))
            elif len(metric_values) == 1:
                print("\t\t{name:s} = {val:.1f} (median: {median:.1f})".format(
                    name=metric_name, val=mean(metric_values), median=median(metric_values)))


def get_results_classes(data, class_list, metric_list, all_classes):
    if all_classes is False:
        for gesture in class_list:
            print("\t" + gesture.capitalize())
            print_metrics(data, metric_list, gesture)
    else:
        print_metrics(data, metric_list)


def get_results_each_classifier(metric_list, data, class_list, all_classes):
    # For each classifier
    for classifier_ in np.unique(data["pre_trained_model"]):
        print(classifier_)
        classif_data = data[data["pre_trained_model"] == classifier_]

        get_results_classes(classif_data, class_list, metric_list, all_classes)


def get_results_summary(df, class_list, metric_list, user_dependent, all_tests, all_classes):

    if user_dependent is not None:
        if user_dependent is True:
            print("######## User Dependent ########")
        else:
            print("######## User Independent ########")

    # if classifier is None:
    #     classifier_list = np.unique(df["classifier"])
    # else:
    #     classifier_list = [classifier]

    if all_tests is True:
        print("All Tests")
    # if win_size is not None and win_overlap is not None:
    #     print(str(win_size) + " s, " + str(win_overlap) + "%")
    # elif all_windows:
    #     print("All window types")
    if all_classes:
        print("All classes")

    if user_dependent is not None:
        if user_dependent:
            data = df[df["subj_dependent"] == 'yes']
        else:
            data = df[df["subj_dependent"] == 'no']
    else:
        data = df

    if all_tests is not None:
        if all_tests is True:
            get_results_each_classifier(metric_list, data, class_list, all_classes)
        else:
            # for each user
            for test in list(set(data["Test_Type"])):
                print("Test " + str(test))

                test_data = df[df["Test_Type"] == test]

                get_results_each_classifier(metric_list, test_data, class_list, all_classes)

    get_results_each_classifier(metric_list, data, class_list, all_classes)


def analyze_results(folder_path, results_folder, data, class_list, print_summary, create_graphs, all_tests, all_classes,
                    compare_dependence, user_dependent, metric_list, test_name):
    # Get path for results folder
    results_folder_path = os.path.join(folder_path, results_folder)
    # Create the folder if it does not exist yet
    if not os.path.exists(results_folder_path):
        os.makedirs(results_folder_path)

    # Classifiers' name to upper
    # data["classifier"] = [i.upper() for i in data["classifier"]]

    if print_summary is True and (compare_dependence is False or compare_dependence is None):
        get_results_summary(data, class_list, metric_list, user_dependent, all_tests, all_classes)
    if create_graphs:
        create_plots(data, class_list, results_folder_path, metric_list, compare_dependence, user_dependent, all_tests,
                     all_classes, test_name)


def plot_cm(folder, cm, labels, percentage=None, subj=None):
    final_cm = cm
    filename = "cm"
    colorbar_title = None
    title = "Confusion Matrix"

    if subj:
        filename += "_subj_" + str(subj)
        title += " - Subject " + str(subj)

    if percentage == "true":
        final_cm = cm / cm.astype(float).sum(axis=1) * 100  # divide by sum of rows (true condition)
        filename += " - recall"
        colorbar_title = "Recall (%)"
    elif percentage == "pred":
        final_cm = cm / cm.astype(float).sum(axis=0) * 100  # divide by sum of columns (predicted condition)
        filename += " - precision"
        colorbar_title = "Precision (%)"

    plt.figure(figsize=(8, 7))
    sns.heatmap(final_cm, annot=True, xticklabels=labels, yticklabels=labels, cmap="Blues", fmt=".1f",
                cbar_kws={'label': colorbar_title})
    plt.xlabel('Predicted')
    plt.ylabel('Actual')
    plt.title(title)

    plt.savefig(os.path.join(folder, filename + ".png"))


def create_cm_figures(folder_path, subj_dependent):
    folder_name = "CM"

    if subj_dependent:
        filename = 'cm_subj_dependent.json'
        folder_name += " User Dependent"
    else:
        filename = 'cm_subj_independent.json'
        folder_name += " User Independent"

    # Create the folder if it does not exist yet
    folder_cm = os.path.join(folder_path, folder_name)
    if not os.path.exists(folder_cm):
        os.makedirs(folder_cm)

    with open(os.path.join(folder_path, filename), 'r') as f:
        data = json.load(f)

    if data:
        labels = data["cm_results"][0]["labels"]
        cm_info = data["cm_results"][0]["cm_info"]

        # for each subject
        cm_subj = []
        for subj in cm_info:
            subj_id = subj["subject"]
            cm_all = subj["cm"]

            mean_cm = np.average(cm_all, axis=0)
            cm_subj.append(mean_cm)

            plot_cm(folder_cm, mean_cm, labels, percentage="true", subj=subj_id)
            plot_cm(folder_cm, mean_cm, labels, percentage="pred", subj=subj_id)

        # for all subjects
        mean_cm = np.average(cm_subj, axis=0)
        plot_cm(folder_cm, mean_cm, labels, percentage="true")
        plot_cm(folder_cm, mean_cm, labels, percentage="pred")


def main():
    # Folder with results
    folder_path = r"C:\DATA\UA\Mestrados_Projetos Licenciatura\2023_24\PECI\RadarID\Resultados"

    class_list = ["ID01", "ID02", "ID03", "ID04", "ID05", "ID06", "ID07"]

    # Summary
    print_summary = True

    # Confusion matrix
    create_cm = False

    # Graphs
    create_graphs = True
    # compare_dependence = False

    # Parameters used only when compare_dependence is False, create_cm is True (user_dependent only),
    # or print_summary is True
    # user_dependent = True
    all_tests = None
    all_classes = False

    metric_list = {"accuracy": "Accuracy",
                   "f1": "F1",
                   # "recall": "Recall",
                   # "precision": "Precision",
                   # "fpr": "FPR"
                   }

    for metric in metric_list:
        metric_list[metric] += " (%)"

    # Get CSV file with results and define the folder where results will be saved to

    test_name = "Captura1" # None usa todas
    file = "Classific_Report.csv"
    if test_name is not None:
        file = test_name + "_" + file

    results_folder = "Graphs"
    # if compare_dependence:
    #     results_folder = "Graphs Both"
    # else:
    #     if user_dependent:
    #         # file = "user_dependent_results.csv"
    #         results_folder = "Graphs User Dependent"
    #     else:
    #         # file = "user_independent_results.csv"
    #         results_folder = "Graphs User Independent"

    # read the file
    with open(os.path.join(folder_path, file)) as csv_file:
        df = pd.read_csv(csv_file)

    if df is not None:
        analyze_results(folder_path, results_folder, df, class_list, print_summary, create_graphs, all_tests,
                        all_classes, None, None, metric_list, test_name)

    if create_cm:
        create_cm_figures(folder_path, None)


if __name__ == "__main__":
    main()
