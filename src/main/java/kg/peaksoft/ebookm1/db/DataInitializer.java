package kg.peaksoft.ebookm1.db;

import kg.peaksoft.ebookm1.entity.*;
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
//    @Bean
//    CommandLineRunner commandLineRunner(
//            UserRepository adminRepository,
//            ClientRepository clientRepository,
//            VendorRepository vendorRepository) {
//        return args -> {
//
//            //img
//            Image image1 = new Image();
//            image1.setName("dystopia");
//
//            Image image2= new Image();
//            image2.setName("detective");
//
//            Image image3 = new Image();
//            image3.setName("poetry");
//
//            //genres
//            Genre genre1 = new Genre();
//            genre1.setBookType(BookType.DYSTOPIA);
//            genre1.setPaperBook(new PaperBook());
//            genre1.setAudioBook(genre1.getAudioBook());
//            genre1.setEBook(genre1.getEBook());

            //Bucket
//            Bucket bucket1 = new Bucket();
//            bucket1 = new Bucket();
//            bucket1.setClient();
//            bucket1.setPaperBooks(Arrays.asList(paperBooks));
//            bucket1.setAudioBooks(Arrays.asList(au));
//            bucket1.setEBooks(Arrays.asList(eBooks1));
//            Bucket bucket2 = new Bucket();
//            bucket2 = new Bucket();
//            bucket2.setClient(clientRepository.findAll());
//            bucket2.setPaperBooks(Arrays.asList(paperBooks));
//            bucket2.setAudioBooks(Arrays.asList(au));
//            bucket2.setEBooks(Arrays.asList(eBooks1));
//            Bucket bucket3 = new Bucket();
//            bucket3 = new Bucket();
//            bucket3.setClient(clientRepository.findAll());
//            bucket3.setPaperBooks(Arrays.asList(paperBooks));
//            bucket3.setAudioBooks(Arrays.asList());
//            bucket3.setEBooks(Arrays.asList(eBooks));
//            Bucket[] buckets={bucket1,bucket2,bucket3};

            //ebook
//            eBook eBooks1 = new eBook();
//            eBooks1.setTitle("1948");
//            eBooks1.setAuthor("George Orwell");
//            eBooks1.setPublishingHouse("Amazon");
//            eBooks1.setPageVolume(500);
//            eBooks1.setPrice(800);
//            eBooks1.setNumberOfBooks(77);
//            eBooks1.setDiscount(25);
//            eBooks1.setBestseller(true);
//            eBooks1.setAboutTheBook("a");
//            eBooks1.setBookLanguage(Language.ENGLISH);
//            eBooks1.setBookType(BookType.DYSTOPIA);
//            eBooks1.setGenres(Arrays.asList(genre1));
//            eBooks1.setImage(Arrays.asList(image1));
//            eBooks1.setYearOfIssue(LocalDate.parse("2000", DateTimeFormatter.ofPattern("yyyy")));
//            eBooks1.setBuckets(Arrays.asList(bucket3));

//            eBook [] eBooks ={eBooks1};
//
//
//           //paperBooks
//            PaperBook paperBook1 = new PaperBook();
//            paperBook1.setTitle("1948");
//            paperBook1.setBookType(BookType.DYSTOPIA);
//            paperBook1.setAuthor("George Orwell");
//            paperBook1.setPublishingHouse("Amazon");
//            paperBook1.setAboutTheBook("awe");
////            paperBook1.setBookFragment("Big Brother always watching you!");
//            paperBook1.setBookLanguage(Language.ENGLISH);
//            paperBook1.setYearOfIssue(LocalDate.parse("2000", DateTimeFormatter.ofPattern("yyyy")));
//            paperBook1.setPageVolume(500);
//            paperBook1.setPrice(800);
//            paperBook1.setNumberOfBooks(7);
//            paperBook1.setDiscount(25);
//            paperBook1.setBestseller(true);
////            paperBook1.setBuckets(Arrays.asList(bucket3));
////            paperBook1.setBuckets(Arrays.asList(buckets));
//            paperBook1.setGenres(Arrays.asList(genre1));
//            paperBook1.setImage(Arrays.asList(image1));
//
//            PaperBook paperBook2 = new PaperBook();
//            paperBook2.setTitle("1948");
//            paperBook2.setBookType(BookType.DYSTOPIA);
//            paperBook2.setAuthor("George Orwell");
//            paperBook2.setPublishingHouse("Amazon");
//            paperBook2.setAboutTheBook("awe");
////            paperBook1.setBookFragment("Big Brother always watching you!");
//            paperBook2.setBookLanguage(Language.ENGLISH);
//            paperBook2.setYearOfIssue(LocalDate.parse("2000", DateTimeFormatter.ofPattern("yyyy")));
//            paperBook2.setPageVolume(500);
//            paperBook2.setPrice(800);
//            paperBook2.setNumberOfBooks(7);
//            paperBook2.setDiscount(25);
//            paperBook2.setBestseller(true);
////            paperBook1.setBuckets(Arrays.asList(bucket3));
////            paperBook2.setBuckets(Arrays.asList(buckets));
//            paperBook2.setGenres(Arrays.asList(genre1));
//            paperBook2.setImage(Arrays.asList(image1));
////
////            //audioBook
//            AudioBook audioBook1 = new AudioBook();
//            audioBook1.setTitle("1948");
//            audioBook1.setBookType(BookType.DYSTOPIA);
//            audioBook1.setPublishingHouse("Amazon");
//            audioBook1.setAboutTheBook("awe");
//            audioBook1.setBookFragment("\"Remember! Big Brother always watching you!\"");
//            audioBook1.setBookLanguage(Language.ENGLISH);
//            audioBook1.setYearOfIssue(LocalDate.parse("2022", DateTimeFormatter.ofPattern("yyyy"))); // only year
//            audioBook1.setPrice(800);
//            audioBook1.setNumberOfBooks(7);
//            audioBook1.setDiscount(25);
//            audioBook1.setBestseller(true);
//            audioBook1.setGenres(Arrays.asList(genre1));
////            audioBook1.setBuckets(Arrays.asList(bucket3));
//            audioBook1.setImage(Arrays.asList(image1));
//            AudioBook [ ]audioBooks ={audioBook1};
//
//           //Vendor
//
//            Vendor vendor1 = new Vendor();
//            vendor1.setName("L");
//            vendor1.setEmail("mask@test.test");
//            vendor1.setPassword("password");
//            vendor1.setPaperBooks(Arrays.asList(paperBook2));
////            vendor1.setAudioBooks(audioBooks);
//            vendor1.setEBooks(Arrays.asList(eBooks1));
//
//           //client
//            Client client1 = new Client();
//            client1.setFirstName("Adam");
//            client1.setLastName("O");
//            client1.setPhoneNumber("+12924248");
//            client1.setEmail("adamO@test.test");
//            client1.setPassword("passs");
//            client1.setEmailConfirm(true);
////            client1.setBucket(Arrays.asList(bucket3));
//            client1.setPaperBooks(Arrays.asList(paperBook2));
//            client1.setAudioBooks(Arrays.asList(audioBook1));
//            client1.setEBooks(Arrays.asList(eBooks1));
//            audioBook1.setImage(Arrays.asList(image1));
////            clientRepository.save(client1);


//            //role
//            Role role1 = new Role();
//            role1.setName("ADMIN");
//
//
//            //user
//            User user = new User();
//            user.setFirstName("Jane");
//            user.setLastName("Casper");
//            user.setEmail("jane@test.test");
//            user.setPassword("pass");
//            user.setCreated(LocalDateTime.now());
//            user.setIsActive(true);
//            user.setRoles(Arrays.asList(role1));
//
//
//           //search
//            Search search1 = new Search();
//            search1.setSearch("keyword");
//            search1.setPaperBook(paperBook2);
//            search1.setAudioBook(audioBook1);
//            search1.setEBook(eBooks1);
//
//
//           //order
//            Order order1 = new Order();
//            order1.setCreated(LocalDateTime.now());
//            order1.setUpdated(LocalDateTime.now());
//            order1.setOrderStatus(OrderStatus.NEW);
//            order1.setSum(1000);
//            order1.setAddress("North");
//            order1.setClient(Arrays.asList(client1));
////            order1.setOrderDetails(Arrays.asList(orderDetails1));
//
//            //orderDetails
//            OrderDetails orderDetail1 = new OrderDetails();
//            orderDetail1.setOrder(order1);
//            orderDetail1.setPaperBooks(Arrays.asList(paperBook2));
//            orderDetail1.setAmount(10);
//            orderDetail1.setPrice(800);
//            orderDetail1.setAudioBooks(Arrays.asList(audioBook1));
//            orderDetail1.setEBooks(Arrays.asList(eBooks1));
//
//
//            //promo
//            PromoCode promoCode1 = new PromoCode();
//            promoCode1.setPromoName("Bro");
//            promoCode1.setStartingDay(LocalDateTime.of(2022, 5, 05, 1, 30));
//            promoCode1.setFinishingDay(LocalDateTime.of(2022, 9, 10, 1, 30));
//            promoCode1.setPercent(20);
//            promoCode1.setPaperBooks(Arrays.asList(paperBook2));
//            promoCode1.setAudioBooks(Arrays.asList(audioBook1));
//            promoCode1.setEBooks(Arrays.asList(eBooks1));
//
//            //favoritesClass
//
//            FavoritesBooks favoritesBook1 = new FavoritesBooks();
//            favoritesBook1.setPaperBooks(paperBook2);
//            favoritesBook1.setAudioBooks(audioBook1);
//            favoritesBook1.setEBooks(eBooks1);



            //BookType

//           BookType[] typeBooks= BookType.valueOf();

            //book
//            eBooks1.seteBook(book.getElectronicBook());
//            eBooks1.setPaperbook(book.getPaperbook());
//            eBooks1.setAudioBooks(Arrays.asList(audiobook1));

//
        };
//    }

//}

