-- Insert 7 categories for groceries
INSERT INTO pfy_category (id, created_at, updated_at, name, description)
VALUES ('8ef8f82a-c1d1-4421-8a0d-89d788a63f88', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'Fruits', 'Fresh fruits for a healthy diet'),
       ('82961ecb-9c26-4451-9d83-061095805fb9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'Vegetables', 'Various types of vegetables for cooking'),
       ('a64cfcae-3bf1-4aee-9e9c-367bdbf56c11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'Dairy', 'Milk, cheese, and other dairy products'),
       ('3b56ff59-1885-41fb-9be3-8a91564f77d2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'Meat', 'Fresh meat and poultry'),
       ('feb317a0-9216-4154-9a24-24072e869b9f', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'Bakery', 'Freshly baked bread and pastries'),
       ('c62f4d35-abce-4369-9a3e-a47bac05933e', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'Healthy Choice', 'Healthy choices approved by EFSA'),
       ('f5ccf33f-d92c-4f73-bdac-3e6028800915', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'Chef Adam Recommendation', 'Hand-picked articles by Chef Adam');

-- Insert specific articles for groceries
INSERT INTO pfy_article (id, created_at, updated_at, name, description, price, currency, categories)
VALUES
    -- Fruits
    ('bc3efdee-9832-4629-beeb-eba0fa66e33d', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Apple', 'Fresh and juicy apple', 1.50, 'EUR',
     '{8ef8f82a-c1d1-4421-8a0d-89d788a63f88, c62f4d35-abce-4369-9a3e-a47bac05933e}'),
    ('ae31b3f4-9da3-460d-8f5f-5287910f36b6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Orange', 'Sweet and tangy orange', 2.00, 'EUR',
     '{8ef8f82a-c1d1-4421-8a0d-89d788a63f88, c62f4d35-abce-4369-9a3e-a47bac05933e}'),
    ('f6198b46-63fa-4ebb-954d-c046d6df9c16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Grapes', 'Sweet and juicy grapes', 3.50, 'EUR', '{8ef8f82a-c1d1-4421-8a0d-89d788a63f88}'),
    ('70435a74-b557-4164-99d7-5656dd6a2665', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Watermelon', 'Refreshing and hydrating watermelon', 4.00, 'EUR',
     '{8ef8f82a-c1d1-4421-8a0d-89d788a63f88, f5ccf33f-d92c-4f73-bdac-3e6028800915}'),
    ('2a0c7665-be20-4acb-9b6c-9ae752ed89fe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Plum', 'Juicy and flavorful plum', 8.43, 'NOK',
     '{8ef8f82a-c1d1-4421-8a0d-89d788a63f88, c62f4d35-abce-4369-9a3e-a47bac05933e}'),

    -- Vegetables
    ('670fca31-4672-47bf-941c-660125affaed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Carrot', 'Fresh and crunchy carrot', 1.20, 'EUR',
     '{82961ecb-9c26-4451-9d83-061095805fb9, c62f4d35-abce-4369-9a3e-a47bac05933e}'),
    ('528345eb-8948-4366-9b11-148eb6b1cbee', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Broccoli', 'Nutritious and delicious broccoli', 1.80, 'EUR',
     '{82961ecb-9c26-4451-9d83-061095805fb9, c62f4d35-abce-4369-9a3e-a47bac05933e, f5ccf33f-d92c-4f73-bdac-3e6028800915}'),
    ('470a66c9-789c-4792-93d2-d885a29826ae', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Tomato', 'Fresh and ripe tomato', 24.89, 'CZK', '{82961ecb-9c26-4451-9d83-061095805fb9}'),
    ('27b3af9c-6d76-44d5-9a09-d23667e2d046', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Bell Pepper', 'Colorful and crunchy bell pepper', 2.00, 'EUR', '{82961ecb-9c26-4451-9d83-061095805fb9}'),
    ('332fd885-7b1a-4aae-a3d8-c8eeeea62b2f', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Spinach', 'Nutrient-rich and versatile spinach', 2.50, 'EUR',
     '{82961ecb-9c26-4451-9d83-061095805fb9, c62f4d35-abce-4369-9a3e-a47bac05933e}'),

    -- Dairy
    ('200b1b07-fe25-430e-a46e-36cbacb87af2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Milk', 'Fresh and creamy milk', 48.99, 'CZK', '{a64cfcae-3bf1-4aee-9e9c-367bdbf56c11}'),
    ('8b1f8191-14af-4f84-9285-a308e4b48a1b', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Cheese', 'Sharp and savory cheese', 75.25, 'CZK',
     '{a64cfcae-3bf1-4aee-9e9c-367bdbf56c11, f5ccf33f-d92c-4f73-bdac-3e6028800915}'),
    ('162c394b-ed35-4fb8-82c7-79df584bd8f1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Yogurt', 'Creamy and delicious yogurt', 19.50, 'CZK',
     '{a64cfcae-3bf1-4aee-9e9c-367bdbf56c11, c62f4d35-abce-4369-9a3e-a47bac05933e}'),

    -- Meat
    ('c3178257-fffa-4aaa-8fe8-3de9ee0347f7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Chicken Breast', 'Lean and tender chicken breast', 4.50, 'EUR',
     '{3b56ff59-1885-41fb-9be3-8a91564f77d2, c62f4d35-abce-4369-9a3e-a47bac05933e, f5ccf33f-d92c-4f73-bdac-3e6028800915}'),
    ('9fb5fc3b-b4db-48c2-9df7-b6c2e6cef341', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Ground Beef', 'Fresh ground beef', 5.00, 'EUR',
     '{3b56ff59-1885-41fb-9be3-8a91564f77d2, f5ccf33f-d92c-4f73-bdac-3e6028800915}'),
    ('5cc363c4-7c5f-4f16-8f2e-2d8a020a009c', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Salmon Fillet', 'Fresh and flavorful salmon fillet', 7.00, 'EUR', '{3b56ff59-1885-41fb-9be3-8a91564f77d2}'),
    ('d3fa85a2-b9da-4ee9-92a2-afa2efdb456a', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Ribeye Steak', 'Juicy and tender ribeye steak', 10.00, 'EUR', '{3b56ff59-1885-41fb-9be3-8a91564f77d2}'),

    -- Bakery
    ('b192897c-b3f8-4e87-9998-a76f28737267', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Baguette', 'Freshly baked baguette', 3.00, 'EUR', '{feb317a0-9216-4154-9a24-24072e869b9f}'),
    ('dfc1bc22-af39-4988-86f6-28a39eb8f7fa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Croissant', 'Buttery and flaky croissant', 2.50, 'EUR',
     '{feb317a0-9216-4154-9a24-24072e869b9f, f5ccf33f-d92c-4f73-bdac-3e6028800915}'),
    ('69417709-1a9a-49e2-a35e-4f0699a52054', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     'Cinnamon Roll', 'Delicious cinnamon roll', 28.99, 'NOK', '{feb317a0-9216-4154-9a24-24072e869b9f}');

