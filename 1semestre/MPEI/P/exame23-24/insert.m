function BF = insert(elemento, BF, k)
    n = length(BF);
    for i = 1:k
        elemento = [elemento num2str(i)];
        h = HashFunction(elemento,k);
        h = mod(h,n) + 1;
        BF(h) = true;
    end
end