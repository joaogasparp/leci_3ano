% N Número de experiências -> 10000
% p Probabilidade de sair o que queremos -> 0.5
% n Número de lançamentos por experiência -> 4
% k Índice -> número que queremos
function pX = fMassaProb (N,p,k,n)
  lancamentos = rand(n,N) < p;
  sucessos = sum(lancamentos) == k;
  pX = sum(sucessos)/N;
end