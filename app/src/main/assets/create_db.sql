-- import to SQLite by running: sqlite3.exe db.sqlite3 -init sqlite.sql

PRAGMA journal_mode = MEMORY;
PRAGMA synchronous = OFF;
PRAGMA foreign_keys = OFF;
PRAGMA ignore_check_constraints = OFF;
PRAGMA auto_vacuum = NONE;
PRAGMA secure_delete = OFF;
BEGIN TRANSACTION;

GO
CONTAINMENT = NONE
ON  PRIMARY
( NAME = N'bookshop', FILENAME = N'C:\Users\phabi\bookshop.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
LOG ON
( NAME = N'bookshop_log', FILENAME = N'C:\Users\phabi\bookshop_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [bookshop] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [bookshop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [bookshop] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [bookshop] SET ANSI_NULLS OFF
GO
ALTER DATABASE [bookshop] SET ANSI_PADDING OFF
GO
ALTER DATABASE [bookshop] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [bookshop] SET ARITHABORT OFF
GO
ALTER DATABASE [bookshop] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [bookshop] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [bookshop] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [bookshop] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [bookshop] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [bookshop] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [bookshop] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [bookshop] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [bookshop] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [bookshop] SET  DISABLE_BROKER
GO
ALTER DATABASE [bookshop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [bookshop] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [bookshop] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [bookshop] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [bookshop] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [bookshop] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [bookshop] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [bookshop] SET RECOVERY SIMPLE
GO
ALTER DATABASE [bookshop] SET  MULTI_USER
GO
ALTER DATABASE [bookshop] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [bookshop] SET DB_CHAINING OFF
GO
ALTER DATABASE [bookshop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO
ALTER DATABASE [bookshop] SET TARGET_RECOVERY_TIME = 60 SECONDS
GO
ALTER DATABASE [bookshop] SET DELAYED_DURABILITY = DISABLED
GO
ALTER DATABASE [bookshop] SET ACCELERATED_DATABASE_RECOVERY = OFF
GO
ALTER DATABASE [bookshop] SET QUERY_STORE = OFF
GO
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[author](
[id_author] [int] IDENTITY(1,1) NOT NULL,
[author_name] [nvarchar](max) NOT NULL,
CONSTRAINT [PK_author] PRIMARY KEY CLUSTERED
(
[id_author] ASC
);
);
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[basket](
[id_basket] [int] IDENTITY(1,1) NOT NULL,
[id_user] [int] NOT NULL,
[id_book] [int] NOT NULL,
CONSTRAINT [PK_basket] PRIMARY KEY CLUSTERED
(
[id_basket] ASC
);
);
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[book](
[id_book] [int] IDENTITY(1,1) NOT NULL,
[book_name] [nvarchar](max) NOT NULL,
[price] [float] NOT NULL,
[id_genre] [int] NOT NULL,
[id_edition] [int] NOT NULL,
[id_author] [int] NOT NULL,
[year] [int] NOT NULL,
[imagepath] [nvarchar](max) NULL,
CONSTRAINT [PK_book] PRIMARY KEY CLUSTERED
(
[id_book] ASC
);
);
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[client](
[id_client] [int] IDENTITY(1,1) NOT NULL,
[id_gender] [int] NOT NULL,
[full_name] [nvarchar](max) NOT NULL,
[birthday] [date] NOT NULL,
[phone] [nvarchar](max) NOT NULL,
CONSTRAINT [PK_client] PRIMARY KEY CLUSTERED
(
[id_client] ASC
);
);
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[edition](
[id_edition] [int] IDENTITY(1,1) NOT NULL,
[edition_name] [nvarchar](max) NOT NULL,
CONSTRAINT [PK_edition] PRIMARY KEY CLUSTERED
(
[id_edition] ASC
);
);
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[gender](
[id_gender] [int] IDENTITY(1,1) NOT NULL,
[gender_name] [nvarchar](max) NOT NULL,
CONSTRAINT [PK_gender] PRIMARY KEY CLUSTERED
(
[id_gender] ASC
);
);
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[genre](
[id_genre] [int] IDENTITY(1,1) NOT NULL,
[genre_name] [nvarchar](max) NOT NULL,
CONSTRAINT [PK_genre] PRIMARY KEY CLUSTERED
(
[id_genre] ASC
);
);
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order](
[id_order] [int] IDENTITY(1,1) NOT NULL,
[id_basket] [int] NOT NULL,
[id_shop] [int] NOT NULL,
CONSTRAINT [PK_order] PRIMARY KEY CLUSTERED
(
[id_order] ASC
);
);
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
[id_role] [int] IDENTITY(1,1) NOT NULL,
[role_name] [nvarchar](max) NOT NULL,
CONSTRAINT [PK_role] PRIMARY KEY CLUSTERED
(
[id_role] ASC
);
);
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[shop](
[id_shop] [int] IDENTITY(1,1) NOT NULL,
[shop_address] [nvarchar](max) NOT NULL,
[open_time] [nvarchar](max) NOT NULL,
CONSTRAINT [PK_shop] PRIMARY KEY CLUSTERED
(
[id_shop] ASC
);
);
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
[id_user] [int] IDENTITY(1,1) NOT NULL,
[id_client] [int] NULL,
[id_role] [int] NOT NULL,
[login] [nvarchar](max) NOT NULL,
[password] [nvarchar](max) NOT NULL,
[countorder] [int] NOT NULL,
CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED
(
[id_user] ASC
);
);
GO
SET IDENTITY_INSERT [dbo].[author] ON
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (1, N'Сара Дж. Маас')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (2, N'Джей Кристофф')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (3, N'Виктория Авеярд')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (4, N'Виктория Шваб')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (5, N'Лия Арден')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (6, N'Дж. Р.Р. Толкин')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (7, N'Эрин А. Крейг')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (8, N'Джоан Роулинг')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (9, N'Чак Паланик')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (10, N'Артур Конан Дойл')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (11, N'Александр Сергеевич Пушкин')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (12, N'Лев Николаевич Толстой')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (13, N'Агата Кристи')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (14, N'Стивен Кинг')
INSERT [dbo].[author] ([id_author], [author_name]) VALUES (15, N'Томас Харрис ')
SET IDENTITY_INSERT [dbo].[author] OFF
GO
SET IDENTITY_INSERT [dbo].[book] ON
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (1, N'Стеклянный трон ', 1455, 1, 1, 1, 2012, N'C:\Users\phabi\Desktop\images\1.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (2, N'Неночь', 1234, 1, 2, 2, 2015, N'C:\Users\phabi\Desktop\images\2.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (3, N'Королевство шипов и роз', 1411, 1, 1, 1, 2021, N'C:\Users\phabi\Desktop\images\3.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (4, N'Алая королева', 699, 1, 3, 3, 2016, N'C:\Users\phabi\Desktop\images\4.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (5, N'Незримая жизнь Адди Ларю', 741, 1, 3, 4, 2017, N'C:\Users\phabi\Desktop\images\5.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (6, N'Мара и Морок ', 3180, 1, 3, 5, 2022, N'C:\Users\phabi\Desktop\images\6.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (7, N'Властелин Колец', 2199, 1, 2, 6, 1974, N'C:\Users\phabi\Desktop\images\7.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (8, N'Дом Соли и Печали', 599, 1, 2, 7, 2019, N'C:\Users\phabi\Desktop\images\8.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (9, N'Хоббит', 999, 1, 2, 6, 1999, N'C:\Users\phabi\Desktop\images\9.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (10, N'Гарри Поттер', 688, 1, 4, 8, 2001, N'C:\Users\phabi\Desktop\images\10.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (11, N'Бойцовский клуб', 577, 3, 3, 9, 2002, N'C:\Users\phabi\Desktop\images\11.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (12, N'Шерлок Холмс', 477, 2, 2, 10, 1985, N'C:\Users\phabi\Desktop\images\12.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (14, N'Евгений Онегин', 688, 4, 2, 11, 1958, N'C:\Users\phabi\Desktop\images\13.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (15, N'Война и мир', 433, 4, 2, 12, 1958, N'C:\Users\phabi\Desktop\images\14.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (16, N'Таинственное дело в Стайлсе', 734, 2, 4, 13, 2014, N'C:\Users\phabi\Desktop\images\15.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (17, N'Кладбище домашних животных', 878, 5, 4, 14, 1983, N'C:\Users\phabi\Desktop\images\17.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (18, N'Город Полумесяца', 1599, 1, 1, 1, 2024, N'C:\Users\phabi\Desktop\images\16.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (19, N'Оно', 999, 5, 3, 14, 1986, N'C:\Users\phabi\Desktop\images\18.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (20, N'Побег из Шоушенка', 678, 3, 4, 14, 2011, N'C:\Users\phabi\Desktop\images\19.jpg')
INSERT [dbo].[book] ([id_book], [book_name], [price], [id_genre], [id_edition], [id_author], [year], [imagepath]) VALUES (21, N'Молчание Ягнат', 466, 5, 3, 15, 2014, N'C:\Users\phabi\Desktop\images\20.jpg')
SET IDENTITY_INSERT [dbo].[book] OFF
GO
SET IDENTITY_INSERT [dbo].[client] ON
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (4, 2, N'Иванов Иван Иванович', CAST(N'2001-06-12' AS Date), N'124124')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (5, 2, N'Климов Егор Евгеньевич', CAST(N'2003-08-14' AS Date), N'235423')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (6, 2, N'Семенов Семен Семенович', CAST(N'1989-12-12' AS Date), N'354738765')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (7, 1, N'Террассенова Аэлина Галатиния', CAST(N'2000-06-11' AS Date), N'234234532')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (8, 1, N'Землеводная Клавдия Никитишна', CAST(N'1966-04-26' AS Date), N'24124422')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (9, 2, N'Прутковский Валентин Сергеевич', CAST(N'2005-01-15' AS Date), N'3241414')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (10, 1, N'Тайская Анна Марковна', CAST(N'2004-09-14' AS Date), N'21431423')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (11, 2, N'Прутиков Виктор Сафронович', CAST(N'2002-06-24' AS Date), N'32758923')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (12, 1, N'Хабибулина Полина Амировна', CAST(N'2003-06-11' AS Date), N'234234324')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (13, 2, N'Европов Джейк Джейкович', CAST(N'1993-05-25' AS Date), N'242834798')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (1005, 1, N'Иванов Иван Иванович', CAST(N'2025-01-07' AS Date), N'3')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (1006, 1, N'Иванов Иван Иванович', CAST(N'2025-01-01' AS Date), N'5')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (2005, 2, N'Корвере Мия Дариявна', CAST(N'1999-01-01' AS Date), N'222')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (2006, 1, N'Малия Хейл Питеровна', CAST(N'2025-01-26' AS Date), N'12')
INSERT [dbo].[client] ([id_client], [id_gender], [full_name], [birthday], [phone]) VALUES (2007, 2, N'Дилан О Браен', CAST(N'2025-01-09' AS Date), N'3')
SET IDENTITY_INSERT [dbo].[client] OFF
GO
SET IDENTITY_INSERT [dbo].[edition] ON
INSERT [dbo].[edition] ([id_edition], [edition_name]) VALUES (1, N'Азбука')
INSERT [dbo].[edition] ([id_edition], [edition_name]) VALUES (2, N'АСТ')
INSERT [dbo].[edition] ([id_edition], [edition_name]) VALUES (3, N'Эксмо')
INSERT [dbo].[edition] ([id_edition], [edition_name]) VALUES (4, N'Махаон')
SET IDENTITY_INSERT [dbo].[edition] OFF
GO
SET IDENTITY_INSERT [dbo].[gender] ON
INSERT [dbo].[gender] ([id_gender], [gender_name]) VALUES (1, N'женский')
INSERT [dbo].[gender] ([id_gender], [gender_name]) VALUES (2, N'мужской')
SET IDENTITY_INSERT [dbo].[gender] OFF
GO
SET IDENTITY_INSERT [dbo].[genre] ON
INSERT [dbo].[genre] ([id_genre], [genre_name]) VALUES (1, N'фэнтези')
INSERT [dbo].[genre] ([id_genre], [genre_name]) VALUES (2, N'детектив')
INSERT [dbo].[genre] ([id_genre], [genre_name]) VALUES (3, N'триллер')
INSERT [dbo].[genre] ([id_genre], [genre_name]) VALUES (4, N'роман')
INSERT [dbo].[genre] ([id_genre], [genre_name]) VALUES (5, N'хоррор')
SET IDENTITY_INSERT [dbo].[genre] OFF
GO
SET IDENTITY_INSERT [dbo].[role] ON
INSERT [dbo].[role] ([id_role], [role_name]) VALUES (1, N'buyer')
INSERT [dbo].[role] ([id_role], [role_name]) VALUES (2, N'moderator')
SET IDENTITY_INSERT [dbo].[role] OFF
GO
SET IDENTITY_INSERT [dbo].[shop] ON
INSERT [dbo].[shop] ([id_shop], [shop_address], [open_time]) VALUES (1, N'Екатерининская улица 19', N'8-21')
INSERT [dbo].[shop] ([id_shop], [shop_address], [open_time]) VALUES (2, N'Садовая улица 7', N'9-22')
INSERT [dbo].[shop] ([id_shop], [shop_address], [open_time]) VALUES (3, N'Лесная улица 61', N'9-21')
SET IDENTITY_INSERT [dbo].[shop] OFF
GO
SET IDENTITY_INSERT [dbo].[user] ON
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (1, 4, 1, N'vanechka', N'7567', 0)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (2, 5, 1, N'egorkapomidorka', N'5679', 0)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (3, 6, 1, N'лягушка', N'7559', 0)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (5, 7, 1, N'Селена', N'1235', 0)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (6, 8, 1, N'квакша', N'2141', 0)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (7, 9, 1, N'прутик', N'2314', 0)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (8, 10, 1, N'энни', N'2131', 0)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (10, 11, 1, N'sprig', N'1313', 5)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (11, 12, 2, N'1', N'1', 0)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (12, 13, 2, N'mod', N'2', 0)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (1013, 2005, 1, N'2', N'2', 0)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (1014, 2006, 1, N'12', N'121', 0)
INSERT [dbo].[user] ([id_user], [id_client], [id_role], [login], [password], [countorder]) VALUES (1015, 2007, 1, N'3', N'3', 2)
SET IDENTITY_INSERT [dbo].[user] OFF
GO
ALTER TABLE [dbo].[basket]  WITH CHECK ADD  CONSTRAINT [FK_basket_book] FOREIGN KEY([id_book])
REFERENCES [dbo].[book] ([id_book])
GO
ALTER TABLE [dbo].[basket] CHECK CONSTRAINT [FK_basket_book]
GO
ALTER TABLE [dbo].[basket]  WITH CHECK ADD  CONSTRAINT [FK_basket_user] FOREIGN KEY([id_user])
REFERENCES [dbo].[user] ([id_user])
GO
ALTER TABLE [dbo].[basket] CHECK CONSTRAINT [FK_basket_user]
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD  CONSTRAINT [FK_book_author] FOREIGN KEY([id_author])
REFERENCES [dbo].[author] ([id_author])
GO
ALTER TABLE [dbo].[book] CHECK CONSTRAINT [FK_book_author]
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD  CONSTRAINT [FK_book_edition] FOREIGN KEY([id_edition])
REFERENCES [dbo].[edition] ([id_edition])
GO
ALTER TABLE [dbo].[book] CHECK CONSTRAINT [FK_book_edition]
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD  CONSTRAINT [FK_book_genre] FOREIGN KEY([id_genre])
REFERENCES [dbo].[genre] ([id_genre])
GO
ALTER TABLE [dbo].[book] CHECK CONSTRAINT [FK_book_genre]
GO
ALTER TABLE [dbo].[client]  WITH CHECK ADD  CONSTRAINT [FK_client_gender] FOREIGN KEY([id_gender])
REFERENCES [dbo].[gender] ([id_gender])
GO
ALTER TABLE [dbo].[client] CHECK CONSTRAINT [FK_client_gender]
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD  CONSTRAINT [FK_order_shop] FOREIGN KEY([id_shop])
REFERENCES [dbo].[shop] ([id_shop])
GO
ALTER TABLE [dbo].[order] CHECK CONSTRAINT [FK_order_shop]
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FK_user_client] FOREIGN KEY([id_client])
REFERENCES [dbo].[client] ([id_client])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FK_user_client]
GO
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FK_user_role] FOREIGN KEY([id_role])
REFERENCES [dbo].[role] ([id_role])
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FK_user_role]
GO
GO
ALTER DATABASE [bookshop] SET  READ_WRITE
GO





COMMIT;
PRAGMA ignore_check_constraints = ON;
PRAGMA foreign_keys = ON;
PRAGMA journal_mode = WAL;
PRAGMA synchronous = NORMAL;
