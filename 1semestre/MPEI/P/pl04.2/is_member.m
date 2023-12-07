function result = is_member(B, element)
    i = 0;
    k = numel(B);
    
    while 1
        i = i + 1;
        elemento = [element num2str(i)];
        h = mod(string2hash(elemento, 'djb2'), numel(B)) + 1;
        if (i == k || B(h) == 0)
            break;
        end
    end

    if (i == k)
        result = (B(h) == 1);
    else
        result = false;
    end
end