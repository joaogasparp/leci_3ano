function B = addElemFBC(B, elemento, k)
    n = length(B);
   
    for i= 1:k
        elemento = [num2str(elemento) num2str(i)];

        h = DJB31MA(elemento, 127);
        h = mod(h,n) + 1;

        B(h) = B(h) + 1;
    end
end

