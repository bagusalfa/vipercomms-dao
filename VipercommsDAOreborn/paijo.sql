
select * from kredit inner join angsur on kredit.norek=angsur.norek














,nama,kredit.tanggal as tglkredit,tgljtempo,kredit.pokok as krpok,angsur.bunga,marketing,angpk,angbg,angsur.tanggal as agstgl,
angsur.pokok as agspok,denda,selisih,saldo






select sum(angpk),sum(angbg),kredit.norek,nama,kredit.tanggal as tglkredit,tgljtempo,kredit.pokok as krpok,angsur.bunga,marketing,angpk,angbg,angsur.tanggal as agstgl,
angsur.pokok as agspok,denda,selisih,saldo
from kredit inner join angsur on kredit.norek=angsur.norek

group by kredit.norek,nama,kredit.tanggal ,tgljtempo,kredit.pokok ,angsur.bunga,marketing,angpk,angbg,angsur.tanggal ,
angsur.pokok ,denda,selisih,saldo

