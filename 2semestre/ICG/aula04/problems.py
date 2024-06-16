import numpy as np

# Matriz de transformação
M = np.array([[1, 0, 0], [0, 1, 0], [0, 0, 1]])

# Coordenadas originais dos vértices do quadrado em coordenadas homogêneas
vertices = np.array([[2, 2, 1], [3, 2, 1], [3, 3, 1], [2, 3, 1]])

# Calcular as novas coordenadas dos vértices
vertices_transformados = np.dot(M, vertices.T).T  # Transpõe vertices porque numpy faz a multiplicação de linha por coluna

print(vertices_transformados)
