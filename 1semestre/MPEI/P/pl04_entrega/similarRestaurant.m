function similarRestaurant(file, rest, MA_2, k2, id, userIDs)
    userIDs_uni = unique(file(:,1))';
    Nu = length(userIDs_uni);
    
    J=zeros(Nu,Nu);
    
    for n1= 1:Nu
        for n2= n1+1:Nu
            J(n1,n2) = sum(MA_2(n1,:)==MA_2(n2,:))/k2;
            J(n2,n1) = sum(MA_2(n1,:)==MA_2(n2,:))/k2;
        end
    end
    
    SimilarUsers= zeros(1,3);
        
    for n1= 1:Nu
        if userIDs_uni(n1) == id
            for n2= 1:Nu
                if J(n1,n2) > SimilarUsers(1,3)
                    SimilarUsers(1,:)= [userIDs_uni(n1) userIDs_uni(n2) J(n1,n2)];
                end
            end
        end
    end
    id2 = SimilarUsers(1,2);
    
    restaurentes = [];
    rlen = 1;
    for i = 1:length(userIDs)
        if userIDs(i) == id2
            restaurentes(rlen) = file(i,2);
            rlen = 1 + rlen;
        end
    end
    
    restIDs = rest(:,1)';
    restName = rest(:,2)';
    restLocal = rest(:,3)';
    fprintf("User ID: %d\n",id2)
    for i = 1:length(restIDs)
        if ismember(restIDs{i},restaurentes)
            fprintf("ID: %d; Nome: %s; Localidade: %s.\n", restIDs{i}, restName{i}, restLocal{i})
        end
    end
end