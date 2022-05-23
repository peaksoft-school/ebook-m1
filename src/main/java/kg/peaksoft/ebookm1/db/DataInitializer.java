package kg.peaksoft.ebookm1.db;

import kg.peaksoft.ebookm1.entity.book.*;
import kg.peaksoft.ebookm1.entity.*;
import kg.peaksoft.ebookm1.entity.enumClass.Language;
import kg.peaksoft.ebookm1.entity.orders.Order;
import kg.peaksoft.ebookm1.entity.orders.OrderDetails;
import kg.peaksoft.ebookm1.entity.orders.OrderStatus;
import kg.peaksoft.ebookm1.entity.otherClass.*;
import kg.peaksoft.ebookm1.repository.BookRepository;
import kg.peaksoft.ebookm1.repository.ClientRepository;
import kg.peaksoft.ebookm1.repository.UserRepository;
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
            UserRepository adminRepository,
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
                genre1.setBookGenre("dystopia");
                genre1.setNumberOfBooks(33);
                // genre2
                Genre genre2 = new Genre();
                genre2.setBookGenre("fantasy");
                genre2.setNumberOfBooks(77);
                //genre3
                Genre genre3 = new Genre();
                genre3.setBookGenre("poetry");
                genre1.setNumberOfBooks(11);

                //eBook
                eBook eBooks1 = new eBook();
                eBooks1.setBookFragment("BIG BROTHER IS WATCHING YOU!");

                //paperBook
                PaperBook paperBook1 = new PaperBook();
                paperBook1.setBookFragment("Никого со мной нет.\n" +
                                                "Я один…\n "+
                                               "И — разбитое зеркало…");

                //audioBook
                AudioBook audioBook1 = new AudioBook();
                audioBook1.setBookFragment("Слушай беззвучие, слушай и наслаждайся тем, чего тебе не давали в жизни, — тишиной.");

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
//                book1.setYearOfIssue(LocalDate.parse("1967" ,DateTimeFormatter.ofPattern("yyyy")));
                book1.setYearOfIssue((byte)1967);
                book1.setAudioBooks(Arrays.asList(audioBook1));


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
//                book2.setYearOfIssue(LocalDate.parse("1948", DateTimeFormatter.ofPattern("yyyy")));
                book1.setYearOfIssue((byte)1967);
                book2.setEBooks(Arrays.asList(eBooks1));

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
//                book3.setYearOfIssue(LocalDate.parse("1926", DateTimeFormatter.ofPattern("yyyy")));
                book1.setYearOfIssue((byte)1967);
                book3.setPaperBooks(Arrays.asList(paperBook1));

                //favoritesClass

                FavoritesBooks favoritesBook1 = new FavoritesBooks();
                favoritesBook1.setBooks(Arrays.asList(book1));

                FavoritesBooks favoritesBook2 = new FavoritesBooks();
                favoritesBook2.setBooks(Arrays.asList(book2));

                FavoritesBooks favoritesBook3 = new FavoritesBooks();
                favoritesBook3.setBooks(Arrays.asList(book3));

                //client1
                Client client1 = new Client();
                client1.setFirstName("Adam");
                client1.setLastName("O");
                client1.setPhoneNumber("+12924248");
                client1.setEmail("adamO@test.test");
                client1.setPassword("pasS");
                client1.setMailing(true);
                client1.setFavoritesBooks(favoritesBook1);

                //client2
                Client client2 = new Client();
                client2.setFirstName("Ua");
                client2.setLastName("Wei");
                client2.setPhoneNumber("+4566924247");
                client2.setEmail("wei@test.test");
                client2.setPassword("password");
                client2.setMailing(false);
                client2.setFavoritesBooks(favoritesBook2);

                //client3
                Client client3 = new Client();
                client3.setFirstName("Sam");
                client3.setLastName("L");
                client3.setPhoneNumber("+7766924247");
                client3.setEmail("sam@test.test");
                client3.setPassword("passwordS");
                client3.setMailing(true);
                client3.setFavoritesBooks(favoritesBook3);


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

                //Jane-user1
                User user1 = new User();
                user1.setFirstName("Jane");
                user1.setLastName("Casper");
                user1.setEmail("jane@test.test");
                user1.setPassword("pass");
                user1.setPhoneNumber("+779979960790");
                user1.setCreated(LocalDateTime.now());
                user1.setActive(true);
                user1.setRoles(Arrays.asList(role2));

                //user-elizabeth
                User elizabeth = new User();
                elizabeth.setFirstName("Elizabeth");
                elizabeth.setLastName("Bennet");
                elizabeth.setEmail("bennet@test.test");
                elizabeth.setPassword("darsi");
                elizabeth.setPhoneNumber("+7700050001");
                elizabeth.setRoles(Arrays.asList(role1));
                elizabeth.setCreated(LocalDateTime.now());
                elizabeth.setActive(true);

                //address
                Address address1 = new Address();
                address1.setCountry("NYC");
                address1.setCity("Long Island");
                address1.setStreet("");
                address1.setIndex(1122);

                Address address2 = new Address();
                address2.setCountry("England");
                address2.setCity("Hertfordshire");
                address2.setStreet("Meryton");
                address2.setIndex(7722);


                //Vendor
                Vendor vendorMask1 = new Vendor();
                vendorMask1.setFirstName("I");
                vendorMask1.setLastName("Mask");
                vendorMask1.setEmail("mask@test.test");
                vendorMask1.setAddress(address1);
                vendorMask1.setPhoneNumber("+990000222");
                vendorMask1.setBooks(Arrays.asList(book1));


                Vendor vendor2 = new Vendor();
                vendor2.setFirstName("I");
                vendor2.setLastName("Mask");
                vendor2.setEmail("mask@test.test");
                vendor2.setAddress(address2);
                vendor2.setPhoneNumber("+990000222");
                vendor2.setBooks(Arrays.asList(book2));


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
                order1.setAddress(address1);
                order1.setClient(Arrays.asList(client3));

                Order order2 = new Order();
                order2.setCreated(LocalDateTime.now());
                order2.setUpdated(LocalDateTime.now());
                order2.setOrderStatus(OrderStatus.PAID);
                order2.setSum(590);
                order2.setAddress(address2);
                order2.setClient(Arrays.asList(client2));

                Order order3 = new Order();
                order3.setCreated(LocalDateTime.now());
                order3.setUpdated(LocalDateTime.now());
                order3.setOrderStatus(OrderStatus.NEW);
                order3.setSum(999);
                order3.setAddress(address1);
                order3.setClient(Arrays.asList(client1));


                //orderDetails
                OrderDetails orderDetail1 = new OrderDetails();
                orderDetail1.setOrder(order3);
                orderDetail1.setAmount(33);
                orderDetail1.setPrice(1000);
                orderDetail1.setBooks(Arrays.asList(book1));

                OrderDetails orderDetail2 = new OrderDetails();
                orderDetail2.setOrder(order3);
                orderDetail2.setAmount(77);
                orderDetail2.setPrice(800);
                orderDetail2.setBooks(Arrays.asList(book2));

                OrderDetails orderDetail3 = new OrderDetails();
                orderDetail3.setOrder(order3);
                orderDetail3.setAmount(11);
                orderDetail3.setPrice(7000);
                orderDetail3.setBooks(Arrays.asList(book3));


                //promo
                PromoCode promoCode1 = new PromoCode();
                promoCode1.setPromoName("BroBig");
                promoCode1.setStartingDay(LocalDateTime.of(2022, 5, 05, 1, 30));
                promoCode1.setFinishingDay(LocalDateTime.of(2022, 9, 10, 1, 30));
                promoCode1.setPercent((byte) 6);
                promoCode1.setBooks(Arrays.asList(book1));



                System.out.println(adminRepository.save(user1));
                System.out.println(vendorRepository.save(vendorMask1));
                System.out.println(vendorRepository.save(vendor2));

//                System.out.println(clientRepository.save(client1));
//                System.out.println(clientRepository.save(client2));




        };
    }

}

