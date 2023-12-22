function searchPlate(rest, MinHashSig)
    str = lower(input('Write a String: ', 's'));
    shingle_size = 3;  % Utilizar o mesmo numero de shingles que na database
    K = size(MinHashSig, 2);  % Usar o K igual ao K utilizado na base de dados para os shingles dos titulos
    threshold = 0.99;  % Definir um threshold que nos é dado

    % Cell array com os shingles da string introduzida
    shinglesAns = {};
    for i = 1:length(str) - shingle_size+1
        shingle = str(i:i+shingle_size-1);
        shinglesAns{i} = shingle;
    end

    % Fazer a MinHash dos shingles da string introduzida
    MinHashString = inf(1,K);
    for j = 1:length(shinglesAns)
        chave = char(shinglesAns{j});
        hash = zeros(1,K);
        for kk = 1:K
            chave = [chave num2str(kk)];
            hash(kk) = DJB31MA(chave, 127);
        end
        MinHashString(1,:) = min([MinHashString(1,:); hash]);
    end

    % Distancia de Jaccard entre a string e cada prato
    distJ = ones(1, size(rest,1));  % array para guardar distancias
    h = waitbar(0,'Calculating');
    for i=1:size(rest, 1)  % cada hashcode da string
        waitbar(i/K, h);
        distJ(i) = sum(MinHashSig(i,:) ~= MinHashString)/K;
    end
    delete(h);
    
    flag = false;  % Fazemos uma flag para saber se houve algum prato encontrado com uma distancia menor ou igual a threshold
    temp = 5;
    for i = 1:temp
        [val, pos] = min(distJ);  % Calcular o valor minimo (mais similaridade)
        if (val <= threshold)  % Se o valor minimo já nao pretencer ao threshold não dá print
            if ~ismissing(rest{pos, 6})
                flag = true;
                fprintf('Nome: %s; Localidade: %s; Prato(s) recomendado(s): %s.\t(%f)\n', rest{pos, 2}, rest{pos, 3}, rest{pos, 6}, val);
            end
        end
        distJ(pos) = 1;  % Retirar esse prato dando uma distancia igual a 1
    end
    
    if (~flag)
        fprintf('No dishes found.\n');
    end
end