package kg.peaksoft.ebookm1.db;

import kg.peaksoft.ebookm1.entity.book.*;
import kg.peaksoft.ebookm1.entity.*;
import kg.peaksoft.ebookm1.entity.enumClass.BookType;
import kg.peaksoft.ebookm1.entity.enumClass.Language;
import kg.peaksoft.ebookm1.entity.orders.OrderDetails;
import kg.peaksoft.ebookm1.entity.otherClass.*;
import kg.peaksoft.ebookm1.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository adminRepository) {
        return args -> {

//                //img
                Image image1 = new Image();
                image1.setImage("dystopia");

                Image image2 = new Image();
                image2.setImage("fantasy");

                Image image3 = new Image();
                image3.setImage("poetry");

                //genre1
                Genre genre1 = new Genre();
                genre1.setBookGenre("dystopia");
                genre1.setQuantityOfBooks(33);

                // genre2
                Genre genre2 = new Genre();
                genre2.setBookGenre("fantasy");
                genre2.setQuantityOfBooks(77);

                //genre3
                Genre genre3 = new Genre();
                genre3.setBookGenre("poetry");
                genre1.setQuantityOfBooks(11);

                //eBook
                eBook eBooks1 = new eBook();
                eBooks1.setBookFragment("BIG BROTHER IS WATCHING YOU!");

                //paperBook
                PaperBook paperBook1 = new PaperBook();
                paperBook1.setFragment("Никого со мной нет.\n" +
                                                "Я один…\n "+
                                               "И — разбитое зеркало…");


                //audioBook
                AudioBook audioBook1 = new AudioBook();
                audioBook1.setAudioFragment("Слушай беззвучие, слушай и наслаждайся тем, чего тебе не давали в жизни, — тишиной.");


                // book1
                Book book1 = new Book();
                book1.setTitle("The Master and Margarita");
                book1.setAuthor("Mikhail Bulgakov");
                book1.setPublishingHouse("МИФ");
                book1.setGenre(genre2);
                book1.setBookLanguage(Language.RUSSIAN);
                book1.setBookType(BookType.AUDIO_BOOK);
                book1.setPageVolume(504);
                book1.setAboutTheBook("awe");
                book1.setBookFragment("Voland");
                book1.setYearOfIssue(1927);
                book1.setPrice(1000);
                book1.setDiscount(5);
                book1.setBestseller(true);
                book1.setAmountOfBooks(88);
                book1.setImage(Arrays.asList(image2));
                book1.setAudioBooks(audioBook1);

                //book2
                Book book2 = new Book();
                book2.setTitle("1984");
                book2.setAuthor("George Orwell");
                book2.setPublishingHouse("Amazon");
                book2.setGenre(genre1);
                book2.setBookLanguage(Language.ENGLISH);
                book2.setBookType(BookType.PAPER_BOOK);
                book2.setPageVolume(504);
                book2.setAboutTheBook("interesting");
                book2.setBookFragment("control");
                book2.setYearOfIssue(1948);
                book2.setPrice(800);
                book2.setDiscount(25);
                book2.setBestseller(true);
                book2.setAmountOfBooks(199);
                book2.setImage(Arrays.asList(image1));
                book2.setEBooks(eBooks1);

                //book3
                Book book3 = new Book();
                book3.setTitle("Черный Человек");
                book3.setAuthor("Сергей Есенин");
                book3.setPublishingHouse("RELX");
                book3.setGenre(genre3);
                book3.setBookLanguage(Language.RUSSIAN);
                book3.setBookType(BookType.ELECTRONIC_BOOK);
                book3.setPageVolume(20);
                book3.setAboutTheBook("brill");
                book3.setBookFragment("Друг мой");
                book3.setYearOfIssue(1926);
                book3.setPrice(700);
                book3.setDiscount(10);
                book3.setBestseller(true);
                book3.setAmountOfBooks(99);
                book3.setImage(Arrays.asList(image3));
                book3.setPaperBooks(paperBook1);

                //favoritesBook1

                FavoritesBooks favoritesBook1 = new FavoritesBooks();
                favoritesBook1.setQuantityOfBooks(22);
                favoritesBook1.setBooks(Arrays.asList(book1));

                //favoritesBook2
                FavoritesBooks favoritesBook2 = new FavoritesBooks();
                favoritesBook2.setQuantityOfBooks(99);
                favoritesBook2.setBooks(Arrays.asList(book2));

                //favoritesBook3
                FavoritesBooks favoritesBook3 = new FavoritesBooks();
                favoritesBook3.setQuantityOfBooks(100);
                favoritesBook3.setBooks(Arrays.asList(book3));

                //promo1
                PromoCode promoCode1 = new PromoCode();
                promoCode1.setPromoName("BroBig");
                promoCode1.setStartingDay(LocalDate.of(2022, 6, 2));
                promoCode1.setFinishingDay(LocalDate.of(2022, 9, 10));
                promoCode1.setPercent((byte) 10);
                promoCode1.setBooks(Arrays.asList(book1));
                
                //promo2
                PromoCode promoHappy = new PromoCode();
                promoHappy.setPromoName("happiness");
                promoHappy.setStartingDay(LocalDate.of(2022, 4, 11));
                promoHappy.setFinishingDay(LocalDate.of(2022, 9, 5));
                promoHappy.setPercent((byte) 7);
                promoHappy.setBooks(Arrays.asList(book2));
                
                //promo3
                PromoCode promobe = new PromoCode();
                promobe.setPromoName("bebe");
                promobe.setStartingDay(LocalDate.of(2022, 1, 30));
                promobe.setFinishingDay(LocalDate.of(2022, 12, 30));
                promobe.setPercent((byte) 3);
                promobe.setBooks(Arrays.asList(book3));

                //address1
                Address address1 = new Address();
                address1.setCountry("NYC");
                address1.setCity("Long Island");
                address1.setAddress("street L");
                address1.setPostCode(1122);

                //address2
                Address address2 = new Address();
                address2.setCountry("England");
                address2.setCity("Hertfordshire");
                address2.setAddress("Meryton");
                address2.setPostCode(7722);




                //orderDetails1
                OrderDetails orderDetail1 = new OrderDetails();
                orderDetail1.setBookOfAmount(33);
                orderDetail1.setSum(1000);
                orderDetail1.setDiscount(20);
                orderDetail1.setPromoCode(promoCode1);
                orderDetail1.setTotalPrice(1299.99);
                orderDetail1.setAddress(address1);


                //orderDetails2
                OrderDetails orderDetail2 = new OrderDetails();
                orderDetail2.setBookOfAmount(77);
                orderDetail2.setSum(1059);
                orderDetail2.setDiscount(10);
                orderDetail2.setPromoCode(promobe);
                orderDetail2.setTotalPrice(9999.99);
                orderDetail2.setAddress(address2);

                //orderDetails3
                OrderDetails orderDetail3 = new OrderDetails();
                orderDetail2.setBookOfAmount(11);
                orderDetail2.setSum(5843);
                orderDetail2.setDiscount(30);
                orderDetail2.setPromoCode(promoHappy);
                orderDetail2.setTotalPrice(5778.9);
                orderDetail2.setAddress(address1);

                //bucket1
                Bucket bucket1 = new Bucket();
                bucket1.setBooks(Arrays.asList(book1));
                bucket1.setOrderDetails(Arrays.asList(orderDetail2));
                bucket1.setAmountOfBooks(33);

                //bucket2
                Bucket bucket2 = new Bucket();
                bucket2.setBooks(Arrays.asList(book2));
                bucket2.setOrderDetails(Arrays.asList(orderDetail1));
                bucket2.setAmountOfBooks(77);

                //bucket3
                Bucket bucket3 = new Bucket();
                bucket3.setBooks(Arrays.asList(book3));
                bucket3.setOrderDetails(Arrays.asList(orderDetail3));
                bucket3.setAmountOfBooks(11);

                //role1
                Role role1 = new Role();
                role1.setName("ADMIN");

                //role2
                Role role2 = new Role();
                role2.setName("CLIENT");

                //role3
                Role role3 = new Role();
                role3.setName("VENDOR");

                //cl
//                Client cl = new Client();
//                cl.setFirstName("Adam");
//                cl.setLastName("O");
//                cl.setPhoneNumber("+12924248");
//                cl.setEmail("adamO@test.test");
//                cl.setPassword("pasS");
//                cl.setMailing(true);
//                cl.setFavoritesBooks(favoritesBook1);
//                cl.setBucket(bucket2);

                //Jane-client1
                User client1 = new User();
                client1.setFirstName("Jane");
                client1.setLastName("Casper");
                client1.setEmail("jane@test.test");
                client1.setPassword("client1");
                client1.setMailing(true);
                client1.setFavoritesBooks(favoritesBook3);
                client1.setBucket(bucket3);
                client1.setCreated(LocalDate.now());
                client1.setRoles(Arrays.asList(role2));

                //uaClient2
                User ua = new User();
                ua.setFirstName("Ua");
                ua.setLastName("Wei");
                ua.setEmail("wei@test.test");
                ua.setPassword("client2");
                ua.setMailing(false);
                ua.setFavoritesBooks(favoritesBook2);
                ua.setBucket(bucket1);
                ua.setCreated(LocalDate.now());
                ua.setRoles(Arrays.asList(role2));


                //samClient3
                User samClient = new User();
                samClient.setFirstName("Sam");
                samClient.setLastName("L");
                samClient.setEmail("sam@test.test");
                samClient.setPassword("passwordClient3");
                samClient.setMailing(true);
                samClient.setFavoritesBooks(favoritesBook3);
                samClient.setBucket(bucket3);
                samClient.setCreated(LocalDate.now());
                samClient.setRoles(Arrays.asList(role2));


                //elizabethClient4
                User elizabeth = new User();
                elizabeth.setFirstName("Elizabeth");
                elizabeth.setLastName("Bennet");
                elizabeth.setEmail("bennet@test.test");
                elizabeth.setPassword("darsi");
                samClient.setMailing(true);
                samClient.setFavoritesBooks(favoritesBook2);
                samClient.setBucket(bucket2);
                elizabeth.setCreated(LocalDate.now());
                elizabeth.setRoles(Arrays.asList(role2));


                //admin
                User admin = new User();
                admin.setFirstName("admin");
                admin.setPassword("passwordadmin");
                admin.setRoles(Arrays.asList(role1));


                //Vendor1
                User mask = new User();
                mask.setFirstName("I");
                mask.setLastName("Mask");
                mask.setEmail("mask@test.test");
                mask.setPhoneNumber("+990000222");
                mask.setRoles(Arrays.asList(role3));

                //vendor2
                User may = new User();
                may.setFirstName("May");
                may.setLastName("Do");
                may.setEmail("may@test.test");
                may.setPhoneNumber("+5550000452");
//                may.setBook(Arrays.asList(book2));  //whito sell book
//                may.setPromoCode(Arrays.asList(promoHappy));
                mask.setRoles(Arrays.asList(role3));

                //vendor3
                User moon = new User();
                moon.setFirstName("Moon");
                moon.setLastName("Sailor");
                moon.setEmail("sailor@test.test");
                moon.setPhoneNumber("+000003344");
                moon.setRoles(Arrays.asList(role3));

                // save

//                System.out.println(adminRepository.save(admin));

//                System.out.println(adminRepository.save(ua));
                System.out.println(adminRepository.save(samClient));
//                System.out.println(adminRepository.save(elizabeth));
//                System.out.println(adminRepository.save(client1));

//                System.out.println(adminRepository.save(moon));
//                System.out.println(adminRepository.save(mask));
//                System.out.println(adminRepository.save(may));



        };
    }

}

