function c = countFBC(B, elemento, k)
    n = length(B);
    pos = zeros(1,k);

    for i = 1:k
        elemento = [num2str(elemento) num2str(i)]; 

        h = DJB31MA(elemento, 127);
        h = mod(h,n) + 1;
        pos(i) = h;
    end
    c = min(B(pos));
end

