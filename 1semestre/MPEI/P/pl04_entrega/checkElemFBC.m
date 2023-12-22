function boll = checkElemFBC(B, elemento,k)
    i = 0;
    n = length(B);

    while 1
        i = i + 1;
        elemento = [num2str(elemento) num2str(i)]; 

        h = DJB31MA(elemento, 127);
        h = mod(h,n) + 1;

        if (i >= k) || (B(h) == 0)
            break;
        end
    end

    if i >= k
        boll = B(h) ~= 0;
    else 
        boll = false;
    end
end

