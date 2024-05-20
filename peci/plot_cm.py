import json
import os

import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns


def plot_cm(path, filename, cm, labels, percentage=None, subj=None):
    final_cm = cm
    # filename = "cm"
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

    if not os.path.exists(os.path.join(path, "CMs")):
        os.mkdir(os.path.join(path, "CMs"))

    plt.savefig(os.path.join(path, "CMs", filename + ".png"))


def create_cm_figures(path, filename):
    print(filename)

    with open(os.path.join(path, filename + ".json"), 'r') as f:
        data = json.load(f)

    if data:
        labels = data["cm_results"][0]["labels"]
        cm_info = data["cm_results"][0]["cm_info"]

        cm_all = cm_info[0]["folds"]

        # i = 1
        # for cm in cm_all:
        #     plot_cm(path, filename + "_" + str(i), cm, labels)
        #     i += 1

        mean_cm = np.average(cm_all, axis=0)
        plot_cm(path, filename, mean_cm, labels)


def main():

    path = r"C:\DATA\UA\Mestrados_Projetos Licenciatura\2023_24\PECI\RadarID\Resultados"

    tests = ["Captura1"]

    for test in tests:
        filename = test + "_Conf_Matrix.json"
        create_cm_figures(path, filename.split(".")[0])


main()
