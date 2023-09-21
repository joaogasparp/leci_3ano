% (a)

function res = probCalc(p, lancamentos, carasPretendidas, experiencias)
    lanc = rand(lancamentos,experiencias) > p;
    success= sum(lanc) == carasPretendidas;
    res = sum(success) / experiencias;
end