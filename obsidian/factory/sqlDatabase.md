support for: 
- sqlite
- MySQL
- MongoDB #TODO
- PostgreSQL #TODO
- Redis #TODO

Table: lines
Columns:
- id (int, auto-incremented, primary key)
- enabled (boolean)

Table: belts
note: create function that takes nonnull valu
Columns: 
- id (int, auto-incremented, primary key)
- location (varchar) #remakeLocation
- target (varchar)
- source (varchar)
- enabled (boolean)
- line_id (int, foreign key referencing lines.id)

Table: belt_items
Columns:
- id (int, auto-incremented, primary key)
- belt_id (int, foreign key referencing belts.id)
- item (varchar) (single itemstack)
- amount (int)

Table: hoppers
Columns: 
- id(int, auto-incremented, primary key)
- target (varchar)
- target_id (int, id of target)
- location (varchar) #remakeLocation
- enabled (boolean)