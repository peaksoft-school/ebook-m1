package kg.peaksoft.ebookm1.db;

import kg.peaksoft.ebookm1.entity.book.*;
import kg.peaksoft.ebookm1.entity.*;
import kg.peaksoft.ebookm1.entity.enumClass.Language;
//import kg.peaksoft.ebookm1.entity.orders.Order;
//import kg.peaksoft.ebookm1.entity.orders.OrderDetails;
//import kg.peaksoft.ebookm1.entity.orders.OrderStatus;
//import kg.peaksoft.ebookm1.entity.otherClass.*;
//import kg.peaksoft.ebookm1.entity.orders.Order;
import kg.peaksoft.ebookm1.entity.orders.OrderDetails;
import kg.peaksoft.ebookm1.entity.otherClass.*;
import kg.peaksoft.ebookm1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
                book1.setPageVolume(504);
                book1.setPrice(1000);
                book1.setDiscount(5);
                book1.setBestseller(true);
                book1.setBookLanguage(Language.RUSSIAN);
                book1.setGenre(genre2);
                book1.setImage(Arrays.asList(image2));
                book1.setYearOfIssue(1927);
//                book1.setYearOfIssue(LocalDate.from(LocalDate.parse("1967" , DateTimeFormatter.ofPattern("yyyy"))));
//                book1.setYearOfIssue((byte)1967);
//                book1.setAudioBooks(Arrays.asList(audioBook1));
                book1.setAudioBooks(audioBook1);



                //book2
                Book book2 = new Book();
                book2.setTitle("1984");
                book2.setAuthor("George Orwell");
                book2.setPublishingHouse("Amazon");
                book2.setPageVolume(504);
                book2.setPrice(800);
                book2.setDiscount(25);
                book2.setBestseller(true);
                book2.setBookLanguage(Language.ENGLISH);
                book2.setGenre(genre1);
                book2.setImage(Arrays.asList(image1));
                book2.setYearOfIssue(1900);
//                book2.setYearOfIssue(LocalDate.parse("1948", DateTimeFormatter.ofPattern("yyyy")));
//                book1.setYearOfIssue((byte)1967);
//                book2.setEBooks(Arrays.asList(eBooks1));
                book2.setEBooks(eBooks1);

                //book3
                Book book3 = new Book();
                book3.setTitle("Черный Человек");
                book3.setAuthor("Сергей Есенин");
                book3.setPublishingHouse("RELX");
                book3.setPageVolume(20);
                book3.setPrice(700);
                book3.setDiscount(10);
                book3.setBestseller(true);
                book3.setBookLanguage(Language.RUSSIAN);
                book3.setGenre(genre3);
                book3.setImage(Arrays.asList(image3));
                book3.setYearOfIssue(2010);
//                book1.setYearOfIssue((byte)1967);
//                book3.setPaperBooks(Arrays.asList(paperBook1));
                book3.setPaperBooks(paperBook1);

                //favoritesClass

                FavoritesBooks favoritesBook1 = new FavoritesBooks();
                favoritesBook1.setBooks(Arrays.asList(book1));

                FavoritesBooks favoritesBook2 = new FavoritesBooks();
                favoritesBook2.setBooks(Arrays.asList(book2));

                FavoritesBooks favoritesBook3 = new FavoritesBooks();
                favoritesBook3.setBooks(Arrays.asList(book3));

                //bucket1
                Bucket bucket1 = new Bucket();
                bucket1.setAmountOfBooks(33);
                bucket1.setBooks(Arrays.asList(book1));

                //bucket2
                Bucket bucket2 = new Bucket();
                bucket2.setAmountOfBooks(77);
                bucket2.setBooks(Arrays.asList(book2));
                //bucket3
                Bucket bucket3 = new Bucket();
                bucket3.setAmountOfBooks(11);
                bucket3.setBooks(Arrays.asList(book3));

                //role
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
                client1.setPassword("pass");
                client1.setMailing(true);
                client1.setFavoritesBooks(favoritesBook3);
                client1.setBucket(bucket3);
                client1.setCreated(LocalDate.now());
                client1.setRoles(Arrays.asList(role2));

                //uaClient
                User ua = new User();
                ua.setFirstName("Ua");
                ua.setLastName("Wei");
                ua.setPhoneNumber("+4566924247");
                ua.setEmail("wei@test.test");
                ua.setPassword(null);
                ua.setPasswordConfirm(null);
                ua.setMailing(false);
                ua.setBeVendor(null);
                ua.setBucket(bucket1);
                ua.setFavoritesBooks(favoritesBook2);
                ua.setBook(null);
                ua.setPromoCode(null);
                ua.setCreated(LocalDate.now());
                ua.setActive(false);
                ua.setRoles(Arrays.asList(role2));


                //samClient
                User samClient = new User();
                samClient.setFirstName("Sam");
                samClient.setLastName("L");
                samClient.setEmail("sam@test.test");
                samClient.setPassword("passwordS");
                samClient.setMailing(true);
                samClient.setFavoritesBooks(favoritesBook3);
                samClient.setBucket(bucket3);
                samClient.setRoles(Arrays.asList(role2));


                //user-elizabeth
                User elizabeth = new User();
                elizabeth.setFirstName("Elizabeth");
                elizabeth.setLastName("Bennet");
                elizabeth.setEmail("bennet@test.test");
                elizabeth.setPassword("darsi");
                samClient.setMailing(true);
                elizabeth.setRoles(Arrays.asList(role2));
                elizabeth.setCreated(LocalDate.now());
                samClient.setFavoritesBooks(favoritesBook2);


                //admin
                User admin = new User();
                admin.setFirstName("Amin");
                admin.setPassword("password");
                admin.setRoles(Arrays.asList(role1));


                //address
                Address address1 = new Address();
                address1.setCountry("NYC");
                address1.setCity("Long Island");
                address1.setAddress("");
                address1.setPostCode(1122);

                Address address2 = new Address();
                address2.setCountry("England");
                address2.setCity("Hertfordshire");
                address2.setAddress("Meryton");
                address2.setPostCode(7722);

                //Vendor
                User mask = new User();
                mask.setFirstName("I");
                mask.setLastName("Mask");
                mask.setEmail("mask@test.test");
                mask.setPhoneNumber("+990000222");
                mask.setPassword("vendor2");
//                mask.setBooks(Arrays.asList(book1));
                mask.setRoles(Arrays.asList(role3));

                //vendor2
                User may = new User();
                may.setFirstName("May");
                may.setLastName("Do");
                may.setEmail("may@test.test");
                may.setPassword("vendor");
                may.setPhoneNumber("+5550000452");
                mask.setRoles(Arrays.asList(role3));



//
//                //search
//                Search search1 = new Search();
//                search1.setSearch("keyword");
//                search1.setBooks(Arrays.asList(book1));

//                //order
//                Order order1 = new Order();
//                order1.setCreated(LocalDateTime.now());
//                order1.setUpdated(LocalDateTime.now());
//                order1.setOrderStatus(OrderStatus.NEW);
//                order1.setSum(1000);
//                order1.setAddress(address1);
//                order1.setClient(Arrays.asList(samClient));
//
//                Order order2 = new Order();
//                order2.setCreated(LocalDateTime.now());
//                order2.setUpdated(LocalDateTime.now());
//                order2.setOrderStatus(OrderStatus.PAID);
//                order2.setSum(590);
//                order2.setAddress(address2);
//                order2.setClient(Arrays.asList(ua));
//
//                Order order3 = new Order();
//                order3.setCreated(LocalDateTime.now());
//                order3.setUpdated(LocalDateTime.now());
//                order3.setOrderStatus(OrderStatus.NEW);
//                order3.setSum(999);
//                order3.setAddress(address1);
//                order3.setClient(Arrays.asList(cl));

                //promo
                PromoCode promoCode1 = new PromoCode();
                promoCode1.setPromoName("BroBig");
                promoCode1.setStartingDay(LocalDate.of(2022, 5, 2));
                promoCode1.setFinishingDay(LocalDate.of(2022, 9, 10));
                promoCode1.setPercent((byte) 6);
                promoCode1.setBooks(Arrays.asList(book1));



                //orderDetails
                OrderDetails orderDetail1 = new OrderDetails();
//                orderDetail1.setOrder(order3);
                orderDetail1.setBookOfAmount(33);
                orderDetail1.setSum(1000);
                orderDetail1.setDiscount(20);
                orderDetail1.setPromoCode(promoCode1);
                orderDetail1.setTotalPrice(1299.99);
                orderDetail1.setAddress(address1);


                OrderDetails orderDetail2 = new OrderDetails();
                orderDetail2.setBookOfAmount(77);
                orderDetail2.setSum(1059);
                orderDetail2.setDiscount(10);
                orderDetail2.setPromoCode(promoCode1);
                orderDetail2.setTotalPrice(9999.99);
                orderDetail2.setAddress(address2);

//                OrderDetails orderDetail3 = new OrderDetails();
////                orderDetail3.setOrder(order3);
//                orderDetail3.(11);
//                orderDetail3(7000);
//                orderDetail3(Arrays.asList(book3));






//                System.out.println(adminRepository.save(admin));
//                System.out.println(adminRepository.save(ua));
                System.out.println(adminRepository.save(samClient));
//                System.out.println(adminRepository.save(elizabeth));

//                System.out.println(adminRepository.save(mask));
//                System.out.println(adminRepository.save(may));

//                System.out.println(clientRepository.save(cl));
//                System.out.println(clientRepository.save(ua));


        };
    }

}

