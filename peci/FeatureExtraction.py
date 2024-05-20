"""
`FeatureExtraction` package is used to retrive a feature of a data sample
"""
from matplotlib import pyplot as plt
from PIL import Image
import numpy as np
from itertools import product


def save_as_grey_image(matrix: list[list], fileName: str) -> None:  
    """
    Function to convert a matrix data into a png image file
    Args:
        - matrix: Matrix of the data, according to the generated histogram,
            which means, X-Y values
        - fileName: Name of the resulting image file
    """
    # Convert matrix to greyscale image (maximum value should be white)
    #print(np.shape(matrix[0]))
    matrix_ = matrix*255/np.max(matrix)

    # matrix_.astype(np.uint8)
    im = Image.fromarray(matrix_)
    # im = im.convert("L")
    im = im.convert("RGB")
    im.save(fileName)


def get_data_type_matrix(data: dict, dataType: str, configInfo: dict, windowSize: float = 2.0) -> list:
    """
    Function to extract the features of a specific data type in a dataset
    Args:
        - data: dictionary with all the recorded data. The keys correspond
        to data_types variable
        - dataTypes: Data type to extract the features
        - configInfo: Radar configuration file
    Returns:
        - List like matrix containing the extracted features
    """
    yRange: list = [min([min(m) for m in data[dataType] if m]), max([max(m) for m in data[dataType] if m])+0.5]
    binY:list = np.arange(yRange[0], yRange[1] + configInfo[dataType]['res'], configInfo[dataType]['res'])
    #binY:list = np.arange(configInfo[dataType]["min"], configInfo[dataType]['max'] + configInfo[dataType]['res'], configInfo[dataType]['res'])
    #yRange: list = [configInfo[dataType]['min'], configInfo[dataType]['max']]

    xTime = []
    yData = []
    nFrames = len(data[dataType])
    elapsed_time = (np.arange(0, nFrames/configInfo['sampling_rate'], 1/configInfo['sampling_rate'], dtype=float))

    for i in range(nFrames):
        n_objs = len(data[dataType][i])
        for j in range(n_objs):
            xTime.append(elapsed_time[i])
            yData.append(data[dataType][i][j])

    if xTime == [] or yData == []: return []

    #matrix, x_edges, y_edges = np.histogram2d(x, y, range=[xRange, yRange], bins=[bin_x, bin_y])
    #matrix = np.rot90(matrix)
    #print(bin_x,bin_y)
    #print(list(matrix.tolist()))
    binX = np.linspace(0,windowSize,nFrames)
   # binX: list = np.arange(min(xTime), max(xTime), configInfo[dataType]['res'])
    matrix, x_edges, y_edges = np.histogram2d(xTime, yData, range=[[0,windowSize], yRange], bins=[binX, binY]) # -> THIS SHOULD WORK RIGHT???
    #matrix, x_edges, y_edges = np.histogram2d(xTime, yData, range=[xRange, yRange], bins=[binY, nFrames])

    #print("Data Type: ", dataType, "; Size: ", np.shape(matrix))
    return matrix.T


def get_feature_matrix(data: dict, dataTypes: list[str], configInfo: dict, windowSize: float = 2.0) -> list:
    
    matrix_all = []
  
    for dataType in dataTypes:

        matrix = get_data_type_matrix(data, dataType, configInfo, windowSize)
        matrix = np.array(matrix)

        if len(matrix_all) == 0:
            matrix_all = matrix
        else:
            matrix_all = np.concatenate((matrix_all, matrix), axis=0)


    return matrix_all


def extract_save_features(data: dict, fileName: str, dataTypes: dict, 
                        configInfo: dict, save: bool, windowSize: float = 2.0) -> None:
    """
    Function to extract the features of a data sample and save them in a
        png format image file
    Args:
        - data: Radar data sample
        - fileName: Name of the resulting image file
        - dataTypes: Data types to take into account, to be used for the 
            feature extraction
        - configInfo: Radar configuration file
        - save: True to save feature, False otherwise
    """
    #dataTypes: list = ["x", "y", "z", "doppler"]
  
    
    sumCount = 0
    
    sumSizes = 0
    for d in ["x","y","z"]:
        sumCount += np.count_nonzero(np.array(data[d],dtype=object))
        sumSizes += np.array(data[d],dtype=object).size

    print(sumCount,sumSizes) 
    if sumSizes == 0:
        return False
    if(sumCount / sumSizes < 0.3):
        return False
    print("generated")
    matrix: list = get_feature_matrix(data, dataTypes, configInfo, windowSize)


    #matrix1d = matrix.sum(axis=1)
   
    # Save each repetition as a greyscale image from the matrix corresponding to all three data types]
    if matrix.size > 0 and save:
        save_as_grey_image(matrix, fileName)
    
    return True


def sliding_window(data: dict, windowSize: float, overlapping: int,
                configInfo: dict, dataTypes: list) -> list[dict]:
    """
    Take a full sample data and slice it pieces of windowSize
    Args:
        - data: full data collected by the radar
        - windowSize: Size of the sliding frame window, in seconds
        - overlapping: Percentage of overlapping or increment between frames
        - configInfo: Radar configuration file
        - dataTypes: list of data_types to include on the segmented samples
    Raises:
        - ValueError: When the supplied overlapping value isn't in the [0,100]
            interval
        - ValueError: When the windowSize argument less than or equal to 0.0
    Returns:
        - List of data samples, on ascendeding order of recording
    """
    if windowSize <= 0:
        raise ValueError("Window Size should be greater than 0.0")

    if overlapping < 0 or overlapping >= 100:
        raise ValueError("Overlapping percentage should be in the [0,100] interval")

    samplingRate: float = configInfo['sampling_rate'] 
    nFrames: int = len(data['x'])
    elapsedTime: list[float] = (np.arange(0, nFrames/samplingRate, 1/samplingRate, dtype=float)).tolist()

    frameDuration: float = (elapsedTime[1] - elapsedTime[0])*1000 # in Ms
    windowSizeFrames: int = int((windowSize * 1000) // frameDuration)
    #totalSampleDuration: float = (frameDuration * (nFrames-1)) / 1000 # in seconds
    
    frameIncrement: int = int((windowSizeFrames * (100 - overlapping)) / 100)
    frameIncrement: int = frameIncrement if frameIncrement > 0 else 1 # Guarantee no infinite loop

    slices: list[dict] = []
    index: int = 0
    while index < nFrames:
        tempData: dict = data.copy()
        tempSlice: dict = {}
        for type in dataTypes:
            tempSlice[type] = tempData[type][index:index+windowSizeFrames]

        # End with last overllaping smaller windows
        tempSliceSize: int = len(tempSlice[dataTypes[0]])
        if tempSliceSize < windowSizeFrames:
            return slices
        slices.append(tempSlice)
        index = index + frameIncrement

    return slices
