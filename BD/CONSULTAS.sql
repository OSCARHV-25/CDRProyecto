use cdr_proyecto_telefonia

-- Consumidor calcular el total de minutos hablados y la tarifa total por cada número de cuenta
select 
	numero_cuenta, 
	numero_del_que_llama as NUMERO_DEL_QUE_LLAMA,
	numero_al_que_se_llama as NUMERO_AL_QUE_SE_LLAMA,
   	SUM(duracion) AS TOTAL_MINUTOS, 
   	SUM(duracion * tarifa) AS TOTAL_TARIFA
from  
	cdr
group by  numero_cuenta ;



-- Consumidor calcular el total de minutos hablados y la tarifa SOLO PARA UNA CUENTA
select 
	numero_cuenta, 
	numero_del_que_llama as NUMERO_DEL_QUE_LLAMA,
	numero_al_que_se_llama as NUMERO_AL_QUE_SE_LLAMA,
	SUM(duracion) AS TOTAL_MINUTOS,
    SUM(duracion * tarifa) AS TOTAL_TARIFA
from 
    cdr
where  
    numero_cuenta = '10175981';
   
   

   
   
   
   
   
   
   
   
   
-- Productor fecha hora que inició el hilo y la cantidad de registros que lleva producidos  
   
 select 
 	id_Productor  as PRODUCTOR,
 	min(hora_producido) as FECHA_HORA_DE_INICIO,
 	count(*) as CANTIDAD_DE_REGISTROS_PRODUCIDOS 
 from 
 	cdr c 

 group by
  id_Productor;
   
   
   
   
  
 
 
 
 
 
 
   
 -- Consumidor : fecha hora que inició el hilo, cantidad de registros que lleva consumidos y total de minutos que lleva procesados por hilo
   
 select 
    id_consumidor as consumidor,
    min(hora_consumido) as fecha_hora_de_inicio,
    count(*) as cantidad_de_registros_consumidos,
    sum(duracion) as total_de_minutos_consumidos
from 
    cdr
group by
    id_consumidor

union all

select 
    'total' as id_consumidor,
    null as fecha_hora_de_inicio,
    count(id_Consumidor) as cantidad_de_registros_consumidos,
    sum(duracion) as total_de_minutos_consumidos
from 
    cdr;
   
   
   
   
   
   
   
   
   

   
 
