SELECT * FROM nass.kanji where kanji_character = '万';
select * from sino_vietnamese where kanji_id = 2630;
select onyomi, count(*) as cnt from onyomi
group by onyomi
having cnt > 1;

select kunyomi, count(*) as cnt from kunyomi
group by kunyomi
having cnt > 1;

select * from sino_vietnamese;
select * from kanji where kanji_character like '嬢';