select f.type_food, tt.boil, tt.fry
from coock_time as tt
join food as f
on tt.food_id = f.id;

select f.type_food, tt.boil
from coock_time as tt
join food as f
on tt.food_id = f.id;

select f.type_food, tt.boil as Варка, tt.fry as Жарка
from coock_time as tt
join food as f
on tt.food_id = f.id;
