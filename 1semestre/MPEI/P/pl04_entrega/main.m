clear;
clc;

rest = readcell('restaurantes.txt', 'Delimiter', '\t');
file = load("utilizadores.data")
userIDs = (file(:,1));
data = load("dados.mat");
id = 0;

while(1)
    clc;
    if (id == 0)
        tmp = input('Insert User ID (1 to ??): ');
        if ~ismember(tmp, userIDs)
            fprintf('Invalid User ID.');
            pause(2);
        else
            id = tmp;
        end
    else
        x = input(['1 - Restaurants evaluated by you' ...
                   '\n2 - Set of restaurants evaluated by the most similar user' ...
                   '\n3 - Search special dish' ...
                   '\n4 - Find most similar restaurants' ...
                   '\n5 - Estimate the number of evaluations by each tourist' ...
                   '\n6 - Exit' ...
                   '\nSelect choice: ']);
        switch x
            case 1
                restaurantYouEvaluated(rest, file, userIDs, id);
                fprintf('\nPress any key to go back.');
                pause;
            case 2
                similarRestaurant(file, rest, data.MA_2, data.k2, id, userIDs);
                fprintf('\nPress any key to go back.');
                pause;
            case 3
                searchPlate(rest, data.MinHashSig);
                fprintf('\nPress any key to go back.');
                pause;
            case 4
                selectRestaurant(rest, file, userIDs, id, data.M, data.k4, data.restAval);
                fprintf('\nPress any key to go back.');
                pause;
            case 5
                tmp = input('Insert User ID: ');
                if ~ismember(tmp, userIDs)
                    fprintf('Invalid User ID.');
                    pause(2);
                else
                    id = tmp;
                    number = countFBC(data.B,id,data.k);
                    fprintf('Number of evaluations made by User ID %d: %d \n', id, number);
                    fprintf('\nPress any key to go back.');
                    pause;
                end
            case 6
                return
            otherwise
                disp('Invalid option.\n')
        end
    end
end

