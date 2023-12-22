file = load("utilizadores.data");
rest = readcell('restaurantes.txt', 'Delimiter','\t');

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% OPCAO 2 
userIDs_uni = unique(file(:,1))';
Nu = length(userIDs_uni);
Set = cell(Nu,1);
userIDs = (file(:,1))';

k2 = 100;

h = waitbar(0, "Creating Set");
for n = 1:Nu
    waitbar(n/Nu,h);
    for i = 1:length(userIDs)
        if userIDs_uni(n) == userIDs(i)
            Set{n} = [Set{n} file(i,2)];
        end
    end
end
delete(h);

MA_2 = inf(Nu,k2);
    
h = waitbar(0, "Creating MA_2");
for i = 1:Nu
    waitbar(i/Nu,h);
    conjunto = Set{i};
    
    for j = 1:length(conjunto)
        elemento = char(conjunto(j));
        hash = zeros(1,k2);
        
        for n = 1:k2
            elemento = [elemento num2str(n)];
            hash(n) = DJB31MA(elemento,127);
        end

        MA_2(i,:)=min([MA_2(i,:);hash]);
    end    
end
delete(h);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% OPCAO 3
shingle_size=3;
K = 100;  % Número de funções de dispersão
MinHashSig = inf(length(rest),K);
for i = 1:length(rest)
    conjunto = lower(rest{i,6});
    shingles = cell(1, length(conjunto) - shingle_size + 1);
    for j= 1 : length(conjunto) - shingle_size+1  % Criacao dos shingles para cada prato
        shingle = conjunto(j:j+shingle_size-1);
        shingles{j} = shingle;
    end
    
    for j = 1:length(shingles)
        chave = char(shingles{j});
        hash = zeros(1,K);
        for kk = 1:K
            chave = [chave num2str(kk)];
            hash(kk) = DJB31MA(chave,127);
        end
        MinHashSig(i,:) = min([MinHashSig(i,:);hash]);  % Valor minimo da hash para este shingle
    end
end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% OPCAO 4
restauranteIDs = rest(:,1);
numRest = length(restauranteIDs);
restAval = zeros(numRest,2);

userIDs = (file(:,1))';

for i = 1:numRest
    restAval(i,1) = restauranteIDs{i};
    avalT = 0;
    c = 0;
    for j = 1:length(userIDs)
        if restauranteIDs{i} == file(j,2)
            c = c + 1;
            avalT = avalT + file(j,4);
        end
    end
    if c == 0
        restAval(i,2) = 0;
    else
        restAval(i,2) = avalT / c;
    end
end

Nr = length(restauranteIDs);
Conjuntos = cell(Nr, 1);

restNM = rest;
for r = 1:Nr
    for i = 1:7
        if ismissing(rest{r,i})
            restNM{r,i} = "";
        end
    end
end

for n = 1:Nr
    Conjuntos{n} = [restNM(n, 3) restNM(n, 4) restNM(n, 5)  restNM(n, 6)  restNM(n, 7)];
end

k4 = 150;
nc = length(Conjuntos);
M = zeros(k4, nc);

for nu = 1:nc
    C = Conjuntos{nu};
    temp = inf(k4, 1);
    for j = 1:length(C)
        chave = char(C{j});
        hash = zeros(k4, 1);

        for kk = 1:k4
            chave_temp = [chave num2str(kk)];
            hash(kk) = DJB31MA(chave_temp, 127);
        end
        temp = min(temp, hash);
    end
    M(:, nu) = temp;
end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% OPCAO 5
userIDs = (file(:,1))';
n = 10000;
B = initFBC(n);

k = 3;
for i = 1:length(userIDs)
    B = addElemFBC(B,userIDs(i),k);
end

save("dados.mat", "B", "k", "MA_2", "k2", "MinHashSig", "M", "k4", "restAval")