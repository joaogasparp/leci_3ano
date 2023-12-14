function chaves = gen_keys(N, imin, imax, caracteres, prob)

    if nargin == 4
        prob = ones(1, length(caracteres)) / length(caracteres);
    end

    chaves = cell(1,N);
    
    for i = 1:N
        comprimento = randi([imin imax]);
        chaves{i} = one_key_gen(comprimento, caracteres, prob);
    end

end

function chave = one_key_gen(comprimento, caracteres, prob)

    chave = '';

    for i = 1:comprimento
        prob_car = prob_caracteres(caracteres, prob);
        chave = strcat(chave, prob_car);
    end

end

function caracteres = prob_caracteres(caracteres, prob)

    U = rand();
    i = 1 + sum(U > cumsum(prob));
    caracteres = caracteres(i);

end