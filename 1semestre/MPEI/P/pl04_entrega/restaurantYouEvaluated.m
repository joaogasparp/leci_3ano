function restaurantYouEvaluated(rest, file, userIDs, id)
    restaurentes = zeros(1, length(userIDs));
    rlen = 1;
    for i = 1:length(userIDs)
        if userIDs(i) == id
            restaurentes(rlen) = file(i,2);
            rlen = 1 + rlen;
        end
    end
    
    restIDs = rest(:,1)';
    restName = rest(:,2)';
    restLocal = rest(:,3)';
    for i = 1:length(restIDs)
        if ismember(restIDs{i},restaurentes)
            fprintf("ID: %d; Nome: %s; Localidade: %s.\n", restIDs{i}, restName{i}, restLocal{i})
        end
    end
end