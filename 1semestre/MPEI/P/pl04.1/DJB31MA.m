function h= DJB31MA( chave, seed)
%
%  chave    array de caracteres com a chave
%  seed     semente que permite obter vários hash codes para a mesma chave
%  h        hashcode devolvido
%
len= length(chave);
chave= double(chave);
h= seed;
for i=1:len
    h = mod(31 * h + chave(i), 2^32 -1) ;
end