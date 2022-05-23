package kg.peaksoft.ebookm1.db;

import kg.peaksoft.ebookm1.entity.book.*;
import kg.peaksoft.ebookm1.entity.enumClass.BookType;
import kg.peaksoft.ebookm1.entity.*;
import kg.peaksoft.ebookm1.entity.enumClass.Language;
import kg.peaksoft.ebookm1.entity.enumClass.OrderStatus;
import kg.peaksoft.ebookm1.entity.otherClass.Genre;
import kg.peaksoft.ebookm1.entity.otherClass.Image;
import kg.peaksoft.ebookm1.entity.otherClass.Search;
import kg.peaksoft.ebookm1.repository.ClientRepository;
import kg.peaksoft.ebookm1.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
public class DataInitializer {
    @Bean
    CommandLineRunner commandLineRunner(
//            UserRepository adminRepository,
            ClientRepository clientRepository,
            VendorRepository vendorRepository) {
        return args -> {

                //img
                Image image1 = new Image();
                image1.setName("dystopia");

                Image image2 = new Image();
                image2.setName("fantasy");

                Image image3 = new Image();
                image3.setName("poetry");



                //genre1
                Genre genre1 = new Genre();
                genre1.setBooks(Arrays.asList(book1));
                genre1.setBookGenre("dystopia");
                // genre2
                Genre genre2 = new Genre();
                genre2.setBookType(BookType.AUDIO_BOOK);
                genre2.setBooks(Arrays.asList(book2));
                genre2.setBookGenre("fantasy");
                //genre3
                Genre genre3 = new Genre();
                genre3.setBookType(BookType.ELECTRONIC_BOOK);
                genre3.setBooks(Arrays.asList(book3));
                genre3.setBookGenre("");

                //book2
                Book book2 = new Book();
                book2.setTitle("1984");
                book2.setAuthor("George Orwell");
                book2.setPublishingHouse("Amazon");
                book2.setPrice(800);
                book2.setDiscount(25);
                book2.setBestseller(true);
                book2.setAboutTheBook("BIG BROTHER IS WATCHING YOU!");
                book2.setBookLanguage(Language.ENGLISH);
                book2.setGenre(genre1);
                book2.setImage(Arrays.asList(image1));
                book2.setYearOfIssue(LocalDate.parse("1948", DateTimeFormatter.ofPattern("yyyy")));
                book2.setBuckets(Arrays.asList(bucket1));
                //book3
                Book book3 = new Book();
                book3.setBookType(BookType.ELECTRONIC_BOOK);

                //client1
                Client client1 = new Client();
                client1.setFirstName("Adam");
                client1.setLastName("O");
                client1.setPhoneNumber("+12924248");
                client1.setEmail("adamO@test.test");
                client1.setPassword("pasS");
                client1.setEmailConfirm(true);
                client1.setBooks(Arrays.asList(book1));

                //client2
                Client client2 = new Client();
                client2.setFirstName("Ua");
                client2.setLastName("Wei");
                client2.setPhoneNumber("+4566924247");
                client2.setEmail("wei@test.test");
                client2.setPassword("password");
                client2.setEmailConfirm(true);
                client2.setBooks(Arrays.asList(book2));

                //client3
                Client client3 = new Client();
                client3.setFirstName("Sam");
                client3.setLastName("L");
                client3.setPhoneNumber("+7766924247");
                client3.setEmail("sam@test.test");
                client3.setPassword("passwordS");
                client3.setEmailConfirm(true);
                client3.setBooks(Arrays.asList(book3));


                //bucket1
                Bucket bucket1 = new Bucket();
                bucket1.setClient(client1);
                bucket1.setBooks(Arrays.asList(book1));

                //bucket2
                Bucket bucket2 = new Bucket();
                bucket2.setClient(client2);
                bucket2.setBooks(Arrays.asList(book2));
                //bucket3
                Bucket bucket3 = new Bucket();
                bucket3.setClient(client3);
                bucket3.setBooks(Arrays.asList(book3));

                //eBook
                eBook eBooks1 = new eBook();


                //ebook2
                eBook eBook2 = new eBook();
                eBook2.setTitle("The Master and Margarita");
                eBook2.setAuthor("Mikhail Bulgakov");
                eBook2.setPublishingHouse("МИФ");
                eBook2.setPageVolume(504);
                eBook2.setPrice(1000);
                eBook2.setNumberOfBooks(66);
                eBook2.setDiscount(5);
                eBook2.setBestseller(true);
                eBook2.setAboutTheBook("Слушай беззвучие, слушай и наслаждайся тем, чего тебе не давали в жизни, — тишиной.");
                eBook2.setBookLanguage(Language.RUSSIAN);
                eBook2.setGenres(Arrays.asList(genre3));
                eBook2.setImage(Arrays.asList(image2));
                eBook2.setYearOfIssue(LocalDate.parse("1967", DateTimeFormatter.ofPattern("yyyy")));
                eBook2.setBuckets(Arrays.asList(bucket2));

                //ebook3
                eBook eBook3 = new eBook();
                eBook3.setTitle("Черный Человек");
                eBook3.setAuthor("Сергей Есенин");
                eBook3.setPublishingHouse("RELX");
                eBook3.setPageVolume(20);
                eBook3.setPrice(700);
                eBook3.setNumberOfBooks(33);
                eBook3.setDiscount(10);
                eBook3.setBestseller(true);
                eBook3.setAboutTheBook("Никого со мной нет.\n" +
                        "Я один…\n" +
                        "И — разбитое зеркало…");
                eBook3.setBookLanguage(Language.RUSSIAN);
                eBook3.setGenres(Arrays.asList(genre3));
                eBook3.setImage(Arrays.asList(image3));
                eBook3.setYearOfIssue(LocalDate.parse("1926", DateTimeFormatter.ofPattern("yyyy")));
                eBook3.setBuckets(Arrays.asList(bucket3));

                //paperBooks
                PaperBook paperBook1 = new PaperBook();
                paperBook1.setTitle("1948");
                paperBook1.setAuthor("George Orwell");
                paperBook1.setPublishingHouse("Amazon");
                paperBook1.setAboutTheBook("awe");
//            paperBook1.setBookFragment("Big Brother always watching you!");
                paperBook1.setBookLanguage(Language.ENGLISH);
                paperBook1.setYearOfIssue(LocalDate.parse("2000", DateTimeFormatter.ofPattern("yyyy")));
                paperBook1.setPageVolume(500);
                paperBook1.setPrice(800);
                paperBook1.setNumberOfBooks(7);
                paperBook1.setDiscount(25);
                paperBook1.setBestseller(true);
                paperBook1.setGenres(Arrays.asList(genre2));
                paperBook1.setImage(Arrays.asList(image1));

                PaperBook paperBook2 = new PaperBook();
                paperBook2.setTitle("1948");
                paperBook2.setAuthor("George Orwell");
                paperBook2.setPublishingHouse("Amazon");
                paperBook2.setAboutTheBook("awe");
//            paperBook1.setBookFragment("Big Brother always watching you!");
                paperBook2.setBookLanguage(Language.ENGLISH);
                paperBook2.setYearOfIssue(LocalDate.parse("2000", DateTimeFormatter.ofPattern("yyyy")));
                paperBook2.setPageVolume(500);
                paperBook2.setPrice(800);
                paperBook2.setNumberOfBooks(7);
                paperBook2.setDiscount(25);
                paperBook2.setBestseller(true);
//            paperBook2.setBuckets(Arrays.asList(buckets));
                paperBook2.setGenres(Arrays.asList(genre2));
                paperBook2.setImage(Arrays.asList(image1));


                //audioBook
                AudioBook audioBook1 = new AudioBook();
                audioBook1.setTitle("Черный человек");
                audioBook1.setAuthor("Сергей Есенин");
                audioBook1.setPublishingHouse("Amazon");
                audioBook1.setAboutTheBook("awe");
                audioBook1.setBookFragment("\"Remember! Big Brother always watching you!\"");
                audioBook1.setBookLanguage(Language.ENGLISH);
                audioBook1.setYearOfIssue(LocalDate.parse("2022", DateTimeFormatter.ofPattern("yyyy"))); // only year
                audioBook1.setPrice(800);
                audioBook1.setNumberOfBooks(7);
                audioBook1.setDiscount(25);
                audioBook1.setBestseller(true);
                audioBook1.getBuckets()
                audioBook1.setGenres(Arrays.asList(genre2));
                audioBook1.setImage(Arrays.asList(image1));


                // book1
                Book book1 = new Book();
                book1.setBookType(BookType.PAPER_BOOK);
                book1.setAudioBooks(Arrays.asList());
                //Vendor

                Vendor vendor1 = new Vendor();
                vendor1.setName("L");
                vendor1.setEmail("mask@test.test");
                vendor1.setPassword("password");
                vendor1.setBooks(Arrays.asList(book1));



                audioBook1.setImage(Arrays.asList(image1));



                //role
                Role role1 = new Role();
                role1.setName("ADMIN");

                //role2
                Role role2 = new Role();
                role2.setName("ADMIN");


                //Jane-user1
                User user1 = new User();
                user1.setFirstName("Jane");
                user1.setLastName("Casper");
                user1.setEmail("jane@test.test");
                user1.setPassword("pass");
                user1.setCreated(LocalDateTime.now());
                user1.setActive(true);
                user1.setRoles(Arrays.asList(role2));

                //user-Elizabeth
                User Elizabeth = new User();
                Elizabeth.setFirstName("Jane");
                Elizabeth.setLastName("Casper");
                Elizabeth.setEmail("jane@test.test");
                Elizabeth.setPassword("pass");
                Elizabeth.setCreated(LocalDateTime.now());
                Elizabeth.setActive(true);




                //search
                Search search1 = new Search();
                search1.setSearch("keyword");
                search1.setBooks(Arrays.asList(book1));

                //order
                Order order1 = new Order();
                order1.setCreated(LocalDateTime.now());
                order1.setUpdated(LocalDateTime.now());
                order1.setOrderStatus(OrderStatus.NEW);
                order1.setSum(1000);
                order1.setAddress("North");
                order1.setClient(Arrays.asList(client3));
//            order1.setOrderDetails(Arrays.asList(orderDetails1));

                //orderDetails
                OrderDetails orderDetail1 = new OrderDetails();
                orderDetail1.setOrder(order1);
                orderDetail1.setAmount(10);
                orderDetail1.setPrice(800);
                orderDetail1.setBooks(Arrays.asList(book1));


                //promo
                PromoCode promoCode1 = new PromoCode();
                promoCode1.setPromoName("Bro");
                promoCode1.setStartingDay(LocalDateTime.of(2022, 5, 05, 1, 30));
                promoCode1.setFinishingDay(LocalDateTime.of(2022, 9, 10, 1, 30));
                promoCode1.setPercent(20);
                promoCode1.setBooks(Arrays.asList(book1));

                //favoritesClass

                FavoritesBooks favoritesBook1 = new FavoritesBooks();
                favoritesBook1.setBooks(Arrays.asList(book1));




        };
    }

}

