-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-10-2025 a las 17:14:36
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `almacen`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`id`, `nombre`, `apellidos`, `correo`) VALUES
(1, 'Juan', 'Pérez', 'juan.perez@northwind.com'),
(2, 'Ana', 'Gómez', 'ana.gomez@northwind.com'),
(3, 'Luis', 'Martínez', 'luis.martinez@northwind.com'),
(4, 'Marta', 'Sánchez', 'marta.sanchez@northwind.com'),
(5, 'Carlos', 'Rodríguez', 'carlos.rodriguez@northwind.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `precio_total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `id_producto`, `descripcion`, `precio_total`) VALUES
(1, 1, 'Pedido de producto 1', 150.00),
(2, 2, 'Pedido de producto 2', 250.00),
(3, 3, 'Pedido de producto 3', 350.00),
(4, 4, 'Pedido de producto 4', 450.00),
(5, 5, 'Pedido de producto 5', 550.00),
(6, 6, 'Pedido de producto 6', 650.00),
(7, 7, 'Pedido de producto 7', 750.00),
(8, 8, 'Pedido de producto 8', 850.00),
(9, 9, 'Pedido de producto 9', 950.00),
(10, 10, 'Pedido de producto 10', 1050.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `descripcion`, `cantidad`, `precio`) VALUES
(1, 'Essence Mascara Lash Princess', 'The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.', 99, 9.99),
(2, 'Eyeshadow Palette with Mirror', 'The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it\'s convenient for on-the-go makeup application.', 34, 19.99),
(3, 'Powder Canister', 'The Powder Canister is a finely milled setting powder designed to set makeup and control shine. With a lightweight and translucent formula, it provides a smooth and matte finish.', 89, 14.99),
(4, 'Red Lipstick', 'The Red Lipstick is a classic and bold choice for adding a pop of color to your lips. With a creamy and pigmented formula, it provides a vibrant and long-lasting finish.', 91, 12.99),
(5, 'Red Nail Polish', 'The Red Nail Polish offers a rich and glossy red hue for vibrant and polished nails. With a quick-drying formula, it provides a salon-quality finish at home.', 79, 8.99),
(6, 'Calvin Klein CK One', 'CK One by Calvin Klein is a classic unisex fragrance, known for its fresh and clean scent. It\'s a versatile fragrance suitable for everyday wear.', 29, 49.99),
(7, 'Chanel Coco Noir Eau De', 'Coco Noir by Chanel is an elegant and mysterious fragrance, featuring notes of grapefruit, rose, and sandalwood. Perfect for evening occasions.', 58, 129.99),
(8, 'Dior J\'adore', 'J\'adore by Dior is a luxurious and floral fragrance, known for its blend of ylang-ylang, rose, and jasmine. It embodies femininity and sophistication.', 98, 89.99),
(9, 'Dolce Shine Eau de', 'Dolce Shine by Dolce & Gabbana is a vibrant and fruity fragrance, featuring notes of mango, jasmine, and blonde woods. It\'s a joyful and youthful scent.', 4, 69.99),
(10, 'Gucci Bloom Eau de', 'Gucci Bloom by Gucci is a floral and captivating fragrance, with notes of tuberose, jasmine, and Rangoon creeper. It\'s a modern and romantic scent.', 91, 79.99),
(11, 'Annibale Colombo Bed', 'The Annibale Colombo Bed is a luxurious and elegant bed frame, crafted with high-quality materials for a comfortable and stylish bedroom.', 88, 1899.99),
(12, 'Annibale Colombo Sofa', 'The Annibale Colombo Sofa is a sophisticated and comfortable seating option, featuring exquisite design and premium upholstery for your living room.', 60, 2499.99),
(13, 'Bedside Table African Cherry', 'The Bedside Table in African Cherry is a stylish and functional addition to your bedroom, providing convenient storage space and a touch of elegance.', 64, 299.99),
(14, 'Knoll Saarinen Executive Conference Chair', 'The Knoll Saarinen Executive Conference Chair is a modern and ergonomic chair, perfect for your office or conference room with its timeless design.', 26, 499.99),
(15, 'Wooden Bathroom Sink With Mirror', 'The Wooden Bathroom Sink with Mirror is a unique and stylish addition to your bathroom, featuring a wooden sink countertop and a matching mirror.', 7, 799.99),
(16, 'Apple', 'Fresh and crisp apples, perfect for snacking or incorporating into various recipes.', 8, 1.99),
(17, 'Beef Steak', 'High-quality beef steak, great for grilling or cooking to your preferred level of doneness.', 86, 12.99),
(18, 'Cat Food', 'Nutritious cat food formulated to meet the dietary needs of your feline friend.', 46, 8.99),
(19, 'Chicken Meat', 'Fresh and tender chicken meat, suitable for various culinary preparations.', 97, 9.99),
(20, 'Cooking Oil', 'Versatile cooking oil suitable for frying, sautéing, and various culinary applications.', 10, 4.99),
(21, 'Cucumber', 'Crisp and hydrating cucumbers, ideal for salads, snacks, or as a refreshing side.', 84, 1.49),
(22, 'Dog Food', 'Specially formulated dog food designed to provide essential nutrients for your canine companion.', 71, 10.99),
(23, 'Eggs', 'Fresh eggs, a versatile ingredient for baking, cooking, or breakfast.', 9, 2.99),
(24, 'Fish Steak', 'Quality fish steak, suitable for grilling, baking, or pan-searing.', 74, 14.99),
(25, 'Green Bell Pepper', 'Fresh and vibrant green bell pepper, perfect for adding color and flavor to your dishes.', 33, 1.29),
(26, 'Green Chili Pepper', 'Spicy green chili pepper, ideal for adding heat to your favorite recipes.', 3, 0.99),
(27, 'Honey Jar', 'Pure and natural honey in a convenient jar, perfect for sweetening beverages or drizzling over food.', 34, 6.99),
(28, 'Ice Cream', 'Creamy and delicious ice cream, available in various flavors for a delightful treat.', 27, 5.49),
(29, 'Juice', 'Refreshing fruit juice, packed with vitamins and great for staying hydrated.', 50, 3.99),
(30, 'Kiwi', 'Nutrient-rich kiwi, perfect for snacking or adding a tropical twist to your dishes.', 99, 2.49),
(31, 'Essence Mascara Lash Princess', 'The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.', 99, 9.99),
(32, 'Eyeshadow Palette with Mirror', 'The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it\'s convenient for on-the-go makeup application.', 34, 19.99),
(33, 'Powder Canister', 'The Powder Canister is a finely milled setting powder designed to set makeup and control shine. With a lightweight and translucent formula, it provides a smooth and matte finish.', 89, 14.99),
(34, 'Red Lipstick', 'The Red Lipstick is a classic and bold choice for adding a pop of color to your lips. With a creamy and pigmented formula, it provides a vibrant and long-lasting finish.', 91, 12.99),
(35, 'Red Nail Polish', 'The Red Nail Polish offers a rich and glossy red hue for vibrant and polished nails. With a quick-drying formula, it provides a salon-quality finish at home.', 79, 8.99),
(36, 'Calvin Klein CK One', 'CK One by Calvin Klein is a classic unisex fragrance, known for its fresh and clean scent. It\'s a versatile fragrance suitable for everyday wear.', 29, 49.99),
(37, 'Chanel Coco Noir Eau De', 'Coco Noir by Chanel is an elegant and mysterious fragrance, featuring notes of grapefruit, rose, and sandalwood. Perfect for evening occasions.', 58, 129.99),
(38, 'Dior J\'adore', 'J\'adore by Dior is a luxurious and floral fragrance, known for its blend of ylang-ylang, rose, and jasmine. It embodies femininity and sophistication.', 98, 89.99),
(39, 'Dolce Shine Eau de', 'Dolce Shine by Dolce & Gabbana is a vibrant and fruity fragrance, featuring notes of mango, jasmine, and blonde woods. It\'s a joyful and youthful scent.', 4, 69.99),
(40, 'Gucci Bloom Eau de', 'Gucci Bloom by Gucci is a floral and captivating fragrance, with notes of tuberose, jasmine, and Rangoon creeper. It\'s a modern and romantic scent.', 91, 79.99),
(41, 'Annibale Colombo Bed', 'The Annibale Colombo Bed is a luxurious and elegant bed frame, crafted with high-quality materials for a comfortable and stylish bedroom.', 88, 1899.99),
(42, 'Annibale Colombo Sofa', 'The Annibale Colombo Sofa is a sophisticated and comfortable seating option, featuring exquisite design and premium upholstery for your living room.', 60, 2499.99),
(43, 'Bedside Table African Cherry', 'The Bedside Table in African Cherry is a stylish and functional addition to your bedroom, providing convenient storage space and a touch of elegance.', 64, 299.99),
(44, 'Knoll Saarinen Executive Conference Chair', 'The Knoll Saarinen Executive Conference Chair is a modern and ergonomic chair, perfect for your office or conference room with its timeless design.', 26, 499.99),
(45, 'Wooden Bathroom Sink With Mirror', 'The Wooden Bathroom Sink with Mirror is a unique and stylish addition to your bathroom, featuring a wooden sink countertop and a matching mirror.', 7, 799.99),
(46, 'Apple', 'Fresh and crisp apples, perfect for snacking or incorporating into various recipes.', 8, 1.99),
(47, 'Beef Steak', 'High-quality beef steak, great for grilling or cooking to your preferred level of doneness.', 86, 12.99),
(48, 'Cat Food', 'Nutritious cat food formulated to meet the dietary needs of your feline friend.', 46, 8.99),
(49, 'Chicken Meat', 'Fresh and tender chicken meat, suitable for various culinary preparations.', 97, 9.99),
(50, 'Cooking Oil', 'Versatile cooking oil suitable for frying, sautéing, and various culinary applications.', 10, 4.99),
(51, 'Cucumber', 'Crisp and hydrating cucumbers, ideal for salads, snacks, or as a refreshing side.', 84, 1.49),
(52, 'Dog Food', 'Specially formulated dog food designed to provide essential nutrients for your canine companion.', 71, 10.99),
(53, 'Eggs', 'Fresh eggs, a versatile ingredient for baking, cooking, or breakfast.', 9, 2.99),
(54, 'Fish Steak', 'Quality fish steak, suitable for grilling, baking, or pan-searing.', 74, 14.99),
(55, 'Green Bell Pepper', 'Fresh and vibrant green bell pepper, perfect for adding color and flavor to your dishes.', 33, 1.29),
(56, 'Green Chili Pepper', 'Spicy green chili pepper, ideal for adding heat to your favorite recipes.', 3, 0.99),
(57, 'Honey Jar', 'Pure and natural honey in a convenient jar, perfect for sweetening beverages or drizzling over food.', 34, 6.99),
(58, 'Ice Cream', 'Creamy and delicious ice cream, available in various flavors for a delightful treat.', 27, 5.49),
(59, 'Juice', 'Refreshing fruit juice, packed with vitamins and great for staying hydrated.', 50, 3.99),
(60, 'Kiwi', 'Nutrient-rich kiwi, perfect for snacking or adding a tropical twist to your dishes.', 99, 2.49),
(61, 'Essence Mascara Lash Princess', 'The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.', 99, 9.99),
(62, 'Eyeshadow Palette with Mirror', 'The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it\'s convenient for on-the-go makeup application.', 34, 19.99),
(63, 'Powder Canister', 'The Powder Canister is a finely milled setting powder designed to set makeup and control shine. With a lightweight and translucent formula, it provides a smooth and matte finish.', 89, 14.99),
(64, 'Red Lipstick', 'The Red Lipstick is a classic and bold choice for adding a pop of color to your lips. With a creamy and pigmented formula, it provides a vibrant and long-lasting finish.', 91, 12.99),
(65, 'Red Nail Polish', 'The Red Nail Polish offers a rich and glossy red hue for vibrant and polished nails. With a quick-drying formula, it provides a salon-quality finish at home.', 79, 8.99),
(66, 'Calvin Klein CK One', 'CK One by Calvin Klein is a classic unisex fragrance, known for its fresh and clean scent. It\'s a versatile fragrance suitable for everyday wear.', 29, 49.99),
(67, 'Chanel Coco Noir Eau De', 'Coco Noir by Chanel is an elegant and mysterious fragrance, featuring notes of grapefruit, rose, and sandalwood. Perfect for evening occasions.', 58, 129.99),
(68, 'Dior J\'adore', 'J\'adore by Dior is a luxurious and floral fragrance, known for its blend of ylang-ylang, rose, and jasmine. It embodies femininity and sophistication.', 98, 89.99),
(69, 'Dolce Shine Eau de', 'Dolce Shine by Dolce & Gabbana is a vibrant and fruity fragrance, featuring notes of mango, jasmine, and blonde woods. It\'s a joyful and youthful scent.', 4, 69.99),
(70, 'Gucci Bloom Eau de', 'Gucci Bloom by Gucci is a floral and captivating fragrance, with notes of tuberose, jasmine, and Rangoon creeper. It\'s a modern and romantic scent.', 91, 79.99),
(71, 'Annibale Colombo Bed', 'The Annibale Colombo Bed is a luxurious and elegant bed frame, crafted with high-quality materials for a comfortable and stylish bedroom.', 88, 1899.99),
(72, 'Annibale Colombo Sofa', 'The Annibale Colombo Sofa is a sophisticated and comfortable seating option, featuring exquisite design and premium upholstery for your living room.', 60, 2499.99),
(73, 'Bedside Table African Cherry', 'The Bedside Table in African Cherry is a stylish and functional addition to your bedroom, providing convenient storage space and a touch of elegance.', 64, 299.99),
(74, 'Knoll Saarinen Executive Conference Chair', 'The Knoll Saarinen Executive Conference Chair is a modern and ergonomic chair, perfect for your office or conference room with its timeless design.', 26, 499.99),
(75, 'Wooden Bathroom Sink With Mirror', 'The Wooden Bathroom Sink with Mirror is a unique and stylish addition to your bathroom, featuring a wooden sink countertop and a matching mirror.', 7, 799.99),
(76, 'Apple', 'Fresh and crisp apples, perfect for snacking or incorporating into various recipes.', 8, 1.99),
(77, 'Beef Steak', 'High-quality beef steak, great for grilling or cooking to your preferred level of doneness.', 86, 12.99),
(78, 'Cat Food', 'Nutritious cat food formulated to meet the dietary needs of your feline friend.', 46, 8.99),
(79, 'Chicken Meat', 'Fresh and tender chicken meat, suitable for various culinary preparations.', 97, 9.99),
(80, 'Cooking Oil', 'Versatile cooking oil suitable for frying, sautéing, and various culinary applications.', 10, 4.99),
(81, 'Cucumber', 'Crisp and hydrating cucumbers, ideal for salads, snacks, or as a refreshing side.', 84, 1.49),
(82, 'Dog Food', 'Specially formulated dog food designed to provide essential nutrients for your canine companion.', 71, 10.99),
(83, 'Eggs', 'Fresh eggs, a versatile ingredient for baking, cooking, or breakfast.', 9, 2.99),
(84, 'Fish Steak', 'Quality fish steak, suitable for grilling, baking, or pan-searing.', 74, 14.99),
(85, 'Green Bell Pepper', 'Fresh and vibrant green bell pepper, perfect for adding color and flavor to your dishes.', 33, 1.29),
(86, 'Green Chili Pepper', 'Spicy green chili pepper, ideal for adding heat to your favorite recipes.', 3, 0.99),
(87, 'Honey Jar', 'Pure and natural honey in a convenient jar, perfect for sweetening beverages or drizzling over food.', 34, 6.99),
(88, 'Ice Cream', 'Creamy and delicious ice cream, available in various flavors for a delightful treat.', 27, 5.49),
(89, 'Juice', 'Refreshing fruit juice, packed with vitamins and great for staying hydrated.', 50, 3.99),
(90, 'Kiwi', 'Nutrient-rich kiwi, perfect for snacking or adding a tropical twist to your dishes.', 99, 2.49),
(91, 'Essence Mascara Lash Princess', 'The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.', 99, 9.99),
(92, 'Eyeshadow Palette with Mirror', 'The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it\'s convenient for on-the-go makeup application.', 34, 19.99),
(93, 'Powder Canister', 'The Powder Canister is a finely milled setting powder designed to set makeup and control shine. With a lightweight and translucent formula, it provides a smooth and matte finish.', 89, 14.99),
(94, 'Red Lipstick', 'The Red Lipstick is a classic and bold choice for adding a pop of color to your lips. With a creamy and pigmented formula, it provides a vibrant and long-lasting finish.', 91, 12.99),
(95, 'Red Nail Polish', 'The Red Nail Polish offers a rich and glossy red hue for vibrant and polished nails. With a quick-drying formula, it provides a salon-quality finish at home.', 79, 8.99),
(96, 'Calvin Klein CK One', 'CK One by Calvin Klein is a classic unisex fragrance, known for its fresh and clean scent. It\'s a versatile fragrance suitable for everyday wear.', 29, 49.99),
(97, 'Chanel Coco Noir Eau De', 'Coco Noir by Chanel is an elegant and mysterious fragrance, featuring notes of grapefruit, rose, and sandalwood. Perfect for evening occasions.', 58, 129.99),
(98, 'Dior J\'adore', 'J\'adore by Dior is a luxurious and floral fragrance, known for its blend of ylang-ylang, rose, and jasmine. It embodies femininity and sophistication.', 98, 89.99),
(99, 'Dolce Shine Eau de', 'Dolce Shine by Dolce & Gabbana is a vibrant and fruity fragrance, featuring notes of mango, jasmine, and blonde woods. It\'s a joyful and youthful scent.', 4, 69.99),
(100, 'Gucci Bloom Eau de', 'Gucci Bloom by Gucci is a floral and captivating fragrance, with notes of tuberose, jasmine, and Rangoon creeper. It\'s a modern and romantic scent.', 91, 79.99),
(101, 'Annibale Colombo Bed', 'The Annibale Colombo Bed is a luxurious and elegant bed frame, crafted with high-quality materials for a comfortable and stylish bedroom.', 88, 1899.99),
(102, 'Annibale Colombo Sofa', 'The Annibale Colombo Sofa is a sophisticated and comfortable seating option, featuring exquisite design and premium upholstery for your living room.', 60, 2499.99),
(103, 'Bedside Table African Cherry', 'The Bedside Table in African Cherry is a stylish and functional addition to your bedroom, providing convenient storage space and a touch of elegance.', 64, 299.99),
(104, 'Knoll Saarinen Executive Conference Chair', 'The Knoll Saarinen Executive Conference Chair is a modern and ergonomic chair, perfect for your office or conference room with its timeless design.', 26, 499.99),
(105, 'Wooden Bathroom Sink With Mirror', 'The Wooden Bathroom Sink with Mirror is a unique and stylish addition to your bathroom, featuring a wooden sink countertop and a matching mirror.', 7, 799.99),
(106, 'Apple', 'Fresh and crisp apples, perfect for snacking or incorporating into various recipes.', 8, 1.99),
(107, 'Beef Steak', 'High-quality beef steak, great for grilling or cooking to your preferred level of doneness.', 86, 12.99),
(108, 'Cat Food', 'Nutritious cat food formulated to meet the dietary needs of your feline friend.', 46, 8.99),
(109, 'Chicken Meat', 'Fresh and tender chicken meat, suitable for various culinary preparations.', 97, 9.99),
(110, 'Cooking Oil', 'Versatile cooking oil suitable for frying, sautéing, and various culinary applications.', 10, 4.99),
(111, 'Cucumber', 'Crisp and hydrating cucumbers, ideal for salads, snacks, or as a refreshing side.', 84, 1.49),
(112, 'Dog Food', 'Specially formulated dog food designed to provide essential nutrients for your canine companion.', 71, 10.99),
(113, 'Eggs', 'Fresh eggs, a versatile ingredient for baking, cooking, or breakfast.', 9, 2.99),
(114, 'Fish Steak', 'Quality fish steak, suitable for grilling, baking, or pan-searing.', 74, 14.99),
(115, 'Green Bell Pepper', 'Fresh and vibrant green bell pepper, perfect for adding color and flavor to your dishes.', 33, 1.29),
(116, 'Green Chili Pepper', 'Spicy green chili pepper, ideal for adding heat to your favorite recipes.', 3, 0.99),
(117, 'Honey Jar', 'Pure and natural honey in a convenient jar, perfect for sweetening beverages or drizzling over food.', 34, 6.99),
(118, 'Ice Cream', 'Creamy and delicious ice cream, available in various flavors for a delightful treat.', 27, 5.49),
(119, 'Juice', 'Refreshing fruit juice, packed with vitamins and great for staying hydrated.', 50, 3.99),
(120, 'Kiwi', 'Nutrient-rich kiwi, perfect for snacking or adding a tropical twist to your dishes.', 99, 2.49),
(121, 'Essence Mascara Lash Princess', 'The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.', 99, 9.99),
(122, 'Eyeshadow Palette with Mirror', 'The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it\'s convenient for on-the-go makeup application.', 34, 19.99),
(123, 'Powder Canister', 'The Powder Canister is a finely milled setting powder designed to set makeup and control shine. With a lightweight and translucent formula, it provides a smooth and matte finish.', 89, 14.99),
(124, 'Red Lipstick', 'The Red Lipstick is a classic and bold choice for adding a pop of color to your lips. With a creamy and pigmented formula, it provides a vibrant and long-lasting finish.', 91, 12.99),
(125, 'Red Nail Polish', 'The Red Nail Polish offers a rich and glossy red hue for vibrant and polished nails. With a quick-drying formula, it provides a salon-quality finish at home.', 79, 8.99),
(126, 'Calvin Klein CK One', 'CK One by Calvin Klein is a classic unisex fragrance, known for its fresh and clean scent. It\'s a versatile fragrance suitable for everyday wear.', 29, 49.99),
(127, 'Chanel Coco Noir Eau De', 'Coco Noir by Chanel is an elegant and mysterious fragrance, featuring notes of grapefruit, rose, and sandalwood. Perfect for evening occasions.', 58, 129.99),
(128, 'Dior J\'adore', 'J\'adore by Dior is a luxurious and floral fragrance, known for its blend of ylang-ylang, rose, and jasmine. It embodies femininity and sophistication.', 98, 89.99),
(129, 'Dolce Shine Eau de', 'Dolce Shine by Dolce & Gabbana is a vibrant and fruity fragrance, featuring notes of mango, jasmine, and blonde woods. It\'s a joyful and youthful scent.', 4, 69.99),
(130, 'Gucci Bloom Eau de', 'Gucci Bloom by Gucci is a floral and captivating fragrance, with notes of tuberose, jasmine, and Rangoon creeper. It\'s a modern and romantic scent.', 91, 79.99),
(131, 'Annibale Colombo Bed', 'The Annibale Colombo Bed is a luxurious and elegant bed frame, crafted with high-quality materials for a comfortable and stylish bedroom.', 88, 1899.99),
(132, 'Annibale Colombo Sofa', 'The Annibale Colombo Sofa is a sophisticated and comfortable seating option, featuring exquisite design and premium upholstery for your living room.', 60, 2499.99),
(133, 'Bedside Table African Cherry', 'The Bedside Table in African Cherry is a stylish and functional addition to your bedroom, providing convenient storage space and a touch of elegance.', 64, 299.99),
(134, 'Knoll Saarinen Executive Conference Chair', 'The Knoll Saarinen Executive Conference Chair is a modern and ergonomic chair, perfect for your office or conference room with its timeless design.', 26, 499.99),
(135, 'Wooden Bathroom Sink With Mirror', 'The Wooden Bathroom Sink with Mirror is a unique and stylish addition to your bathroom, featuring a wooden sink countertop and a matching mirror.', 7, 799.99),
(136, 'Apple', 'Fresh and crisp apples, perfect for snacking or incorporating into various recipes.', 8, 1.99),
(137, 'Beef Steak', 'High-quality beef steak, great for grilling or cooking to your preferred level of doneness.', 86, 12.99),
(138, 'Cat Food', 'Nutritious cat food formulated to meet the dietary needs of your feline friend.', 46, 8.99),
(139, 'Chicken Meat', 'Fresh and tender chicken meat, suitable for various culinary preparations.', 97, 9.99),
(140, 'Cooking Oil', 'Versatile cooking oil suitable for frying, sautéing, and various culinary applications.', 10, 4.99),
(141, 'Cucumber', 'Crisp and hydrating cucumbers, ideal for salads, snacks, or as a refreshing side.', 84, 1.49),
(142, 'Dog Food', 'Specially formulated dog food designed to provide essential nutrients for your canine companion.', 71, 10.99),
(143, 'Eggs', 'Fresh eggs, a versatile ingredient for baking, cooking, or breakfast.', 9, 2.99),
(144, 'Fish Steak', 'Quality fish steak, suitable for grilling, baking, or pan-searing.', 74, 14.99),
(145, 'Green Bell Pepper', 'Fresh and vibrant green bell pepper, perfect for adding color and flavor to your dishes.', 33, 1.29),
(146, 'Green Chili Pepper', 'Spicy green chili pepper, ideal for adding heat to your favorite recipes.', 3, 0.99),
(147, 'Honey Jar', 'Pure and natural honey in a convenient jar, perfect for sweetening beverages or drizzling over food.', 34, 6.99),
(148, 'Ice Cream', 'Creamy and delicious ice cream, available in various flavors for a delightful treat.', 27, 5.49),
(149, 'Juice', 'Refreshing fruit juice, packed with vitamins and great for staying hydrated.', 50, 3.99),
(150, 'Kiwi', 'Nutrient-rich kiwi, perfect for snacking or adding a tropical twist to your dishes.', 99, 2.49);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_fav`
--

CREATE TABLE `productos_fav` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos_fav`
--

INSERT INTO `productos_fav` (`id`, `id_producto`) VALUES
(1, 11),
(3, 11),
(2, 12),
(4, 12),
(5, 41),
(6, 42),
(7, 71),
(8, 72);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos_fav`
--
ALTER TABLE `productos_fav`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producto` (`id_producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=151;

--
-- AUTO_INCREMENT de la tabla `productos_fav`
--
ALTER TABLE `productos_fav`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`);

--
-- Filtros para la tabla `productos_fav`
--
ALTER TABLE `productos_fav`
  ADD CONSTRAINT `productos_fav_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
