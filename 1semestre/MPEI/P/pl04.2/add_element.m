function h = add_element(B, element, k)
    for i = 1 : k
        elemento = [element num2str(i)];
        h = mod(string2hash(elemento, 'djb2'), numel(B)) + 1;
        B(h) = 1;
    end
end