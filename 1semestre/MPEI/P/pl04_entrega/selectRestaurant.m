function option = selectRestaurant(rest, file, userIDs, id, M, k4, restAval)
    restaurentes = zeros(1, length(userIDs));
    rlen = 1;
    
    for i = 1:length(userIDs)
        if userIDs(i) == id
            restaurentes(rlen) = file(i, 2);
            rlen = 1 + rlen;
        end
    end
    
    restIDs = rest(:, 1)';
    restName = rest(:, 2)';
    restLocal = rest(:, 3)';
    
    for i = 1:length(restIDs)
        if ismember(restIDs{i}, restaurentes)
            fprintf("ID: %d; Nome: %s; Localidade: %s.\n", restIDs{i}, restName{i}, restLocal{i})
        end
    end
    
    restIds_int = zeros(length(restIDs));
    for i = 1:length(restIDs)
        if ismember(restIDs{i}, restaurentes)
            restIds_int(i) = restIDs{i};
        end
    end

    option = input('Select an ID: ');
    if ismember(option,restIds_int)
        fprintf('Valid')
    else
        clc;
        fprintf('Invalid option.')
        return
    end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    clc;
    nm = length(M);
    % matriz de similaridade
    J = zeros(nm,nm);
    
    for n1 = 1:nm
        for n2 = n1 + 1:nm
            J(n1, n2) = sum(M(:, n1) == M(:, n2)) / k4;
            J(n2, n1) = J(n1, n2);
        end
    end

    n1 = option;
    
    topSimilarities = [];
    for n2 = 1:nm     
        topSimilarities = [topSimilarities; n1, n2, J(n1, n2), restAval(n2,2)];
    end
    
    sortedSimilarities = sortrows(topSimilarities, [-3, -4]);
    Top3IDs = [sortedSimilarities(1,2) sortedSimilarities(2,2) sortedSimilarities(3,2)];
    
    for i = 1:length(Top3IDs)
        id4 = Top3IDs(i); 
        fprintf("ID: %d; Nome: %s; Localidade: %s.\n", restIDs{id4}, restName{id4}, restLocal{id4})
    end

end