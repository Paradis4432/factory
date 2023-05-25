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
note: create function that takes nonnull value
Columns: 
- id (int, auto-incremented, primary key)
- location (varchar) #remakeLocation
- target (varchar) #remakeLocation 
- target_id (int)
- source (varchar) #remakeLocation
- source_id (int)
- source_name (varchar, name of target such as "belt", " #crafter ", etc)
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
- target (varchar) #remakeLocation
- target_id (int) 
- target_name (varchar, name of target such as "belt", " #crafter ", etc)
- location (varchar) #remakeLocation
- enabled (boolean)
- line_id (int, foreign key referencing lines.id)

Table: inserters
Columns:
- id(int, auto-incremented, primary key)
- target (varchar) #remakeLocation
- target_id (int)
- target_name (varchar, name of target such as "belt", " #crafter ", etc)
- source (varchar) #remakeLocation 
- source_id (int) 
- source_name (varchar, name of target such as "belt", " #crafter ", etc)
- enabled (boolean)
- line_id (int, foreign key referencing lines.id)

Table: storage
Columns: 
- id(int, auto-incremented, primary key)
- source (varchar) #remakeLocation 
- source_id (int) 
- source_name (varchar, name of target such as "belt", " #crafter ", etc)
- enabled (boolean)
- line_id (int, foreign key referencing lines.id)