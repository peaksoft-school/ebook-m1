//package kg.peaksoft.ebookm1.db.services;
//
//import kg.peaksoft.ebookm1.api.payload.book.BookRequest;
//import kg.peaksoft.ebookm1.api.payload.promocode.PromocodeRequest;
//import kg.peaksoft.ebookm1.api.payload.vendor.VendorRequest;
//import kg.peaksoft.ebookm1.api.payload.vendor.VendorResponse;
//import kg.peaksoft.ebookm1.db.entity.Book;
//import kg.peaksoft.ebookm1.db.entity.Promocode;
//import kg.peaksoft.ebookm1.db.entity.Role;
//import kg.peaksoft.ebookm1.db.entity.User;
//import kg.peaksoft.ebookm1.db.enums.BookLanguage;
//import kg.peaksoft.ebookm1.db.mapper.BookEditMapper;
//import kg.peaksoft.ebookm1.db.mapper.PromocodeEditMapper;
//import kg.peaksoft.ebookm1.db.mapper.VendorEditMapper;
//import kg.peaksoft.ebookm1.db.mapper.VendorViewMapper;
//import kg.peaksoft.ebookm1.db.repository.BookRepository;
//import kg.peaksoft.ebookm1.db.repository.PromocodeRepository;
//import kg.peaksoft.ebookm1.db.repository.UserRepository;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static kg.peaksoft.ebookm1.db.enums.Genre.ACTION_ADVENTURE;
//import static kg.peaksoft.ebookm1.db.enums.RequestStatus.SUBMITTED;
//import static kg.peaksoft.ebookm1.db.enums.TypeOfBook.PAPER_BOOK;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class VendorServiceTest {
//
//    @Mock
//    private BCryptPasswordEncoder passwordEncoder;
//    @Mock
//    private UserRepository repository;
//    @Mock
//    private VendorEditMapper editMapper;
//    @Mock
//    private VendorViewMapper viewMapper;
//    @Mock
//    private BookEditMapper bookEditMapper;
//    @Mock
//    private BookRepository bookRepository;
//    @Mock
//    private PromocodeEditMapper promocodeEditMapper;
//    @Mock
//    private PromocodeRepository promocodeRepository;
//    @InjectMocks
//    private VendorService vendorService;
//
//    private User vendor;
//    private BookRequest bookRequest;
//    private Book book1;
//    private VendorResponse vendorResponse;
//    private VendorRequest vendorRequest;
//
//    @BeforeEach
//    void prepare() {
//        book1 = Book.builder()
//                .id(5L)
//                .aboutTheBook("about the book1")
//                .amountOfBooks(10)
//                .author("Pushkin")
//                .bestseller(true)
//                .bookFragment("book fragment")
//                .bookLanguage(BookLanguage.RUSSIAN)
//                .discount(10)
//                .title("Evgenii Onegin")
//                .genreEnum(ACTION_ADVENTURE)
//                .typeOfBook(PAPER_BOOK)
//                .build();
//
//        bookRequest = BookRequest.builder()
//                .aboutTheBook("about the book1")
//                .amountOfBooks(10)
//                .author("Pushkin")
//                .basketPrice(1.0)
//                .bestseller(true)
//                .bookFragment("book fragment")
//                .bookLanguage(BookLanguage.RUSSIAN)
//                .title("Evgenii Onegin")
//                .comments("comment1")
//                .discount(10)
//                // .status(SUBMITTED)
//                .genreEnum(ACTION_ADVENTURE)
//                .typeOfBook(PAPER_BOOK)
//                .build();
//
//
//        var vendorRole = new Role();
//        vendorRole.setName("ROLE_VENDOR");
//
//        vendor = User.builder()
//                .id(1L)
//                .email("vendor@gmail.com")
//                .password("1234567")
//                .roles(List.of(vendorRole))
//                .firstName("Vendor")
//                .lastName("Vendor")
//                .books(List.of(book1))
//                .phoneNumber("+797979797")
//                .build();
//
//        vendorResponse = VendorResponse.builder()
//                .id(1L)
//                .email("vendor@gmail.com")
//                //.bookList(List.of(book1))
//                .firstName("Vendor")
//                .lastName("Vendor")
//                .phoneNumber("+797979797")
//                .build();
//
//        vendorRequest = VendorRequest.builder()
//                .email("vendor@gmail.com")
//                .password("1234567")
//                .firstName("Vendor")
//                .lastName("Vendor")
//                .phoneNumber("+797979797")
//                .build();
//    }
//
//    @Test
//    @DisplayName("Test for register vendor")
//    void register() {
//        doReturn(vendor).when(editMapper).createVendor(vendorRequest);
//        String password = "1234567";
//        doReturn(password).when(passwordEncoder).encode(vendorRequest.getPassword());
//        vendorResponse.setIsActive(true);
//        doReturn(vendor).when(repository).save(vendor);
//        doReturn(vendorResponse).when(viewMapper).viewVendor(vendor);
//
//        assertThat(vendorService.register(vendorRequest)).isEqualTo(vendorResponse);
//        assertThat(vendorService.register(vendorRequest).getIsActive()).isTrue();
//    }
//
//    @Test
//    @DisplayName("Test for update vendor")
//    void update() {
//        doReturn(Optional.of(vendor)).when(repository).findById(1L);
//        doReturn(vendor).when(editMapper).updateVendor(vendor, vendorRequest);
//        doReturn(vendor).when(repository).save(vendor);
//        vendorResponse.setFirstName("updatedFirstname");
//        doReturn(vendorResponse).when(viewMapper).viewVendor(vendor);
//
//        assertThat(vendorService.update(1L, vendorRequest)).isEqualTo(vendorResponse);
//        assertThat(vendorService.update(1L, vendorRequest).getFirstName()).isEqualTo("updatedFirstname");
//
//    }
//
//    @Test
//    @DisplayName("Test for delete by id vendor")
//    void deleteById() {
//        doReturn(Optional.of(vendor)).when(repository).findById(1L);
//        doNothing().when(repository).deleteById(vendor.getId());
//        doReturn(vendorResponse).when(viewMapper).viewVendor(vendor);
//
//        assertThat(vendorService.deleteById(1L)).isEqualTo(vendorResponse);
//        assertThat(vendorService.deleteById(1L)).isEqualTo(vendorResponse);
//    }
//
//    @Test
//    @DisplayName("Test for get all vendors if existed")
//    void getAllVendors() {
//        List<VendorResponse> vendorResponseList = new ArrayList<>();
//        vendorResponseList.add(vendorResponse);
//        doReturn(vendorResponseList).when(viewMapper).viewVendors();
//
//        assertThat(vendorService.getAllVendors().size()).isEqualTo(vendorResponseList.size());
//    }
//
//    @Test
//    @DisplayName("Test for get vendor by id")
//    void gitById() {
//        doReturn(Optional.of(vendor)).when(repository).findById(1L);
//        doReturn(vendorResponse).when(viewMapper).viewVendorById(vendor);
//
//        assertThat(vendorService.gitById(1L)).isEqualTo(vendorResponse);
//    }
//
//    @Test
//    @DisplayName("Test for add book to vendor")
//    void addBookToVendor() {
//        doReturn(book1).when(bookEditMapper).createBook(bookRequest);
//        book1.setStatus(SUBMITTED);
//        doReturn(Optional.of(vendor)).when(repository).findById(1L);
//        book1.setUser(vendor);
//        doReturn(book1).when(bookRepository).save(book1);
//        doReturn(vendor).when(repository).save(vendor);
//        vendorResponse.setBookList(List.of(book1));
//        doReturn(vendorResponse).when(viewMapper).viewVendor(vendor);
//        assertThat(vendorService.addBookToVendor(bookRequest, 1L)).isEqualTo(vendorResponse);
//        assertThat(vendorService.addBookToVendor(bookRequest, 1L).getBookList()).isNotEmpty();
//    }
//
//    @Test
//    @DisplayName("Test for update vendor")
//    void updateBookVendor() {
//        vendorResponse.setBookList(List.of(book1));
//        doReturn(Optional.of(vendor)).when(repository).findById(1L);
//        doReturn(Optional.of(book1)).when(bookRepository).findById(5L);
//
//        doReturn(book1).when(bookEditMapper).updateBook(book1, bookRequest);
//        doReturn(book1).when(bookRepository).save(book1);
//        doReturn(vendor).when(repository).save(vendor);
//        doReturn(vendorResponse).when(viewMapper).viewVendor(vendor);
//
//        assertThat(vendorService.updateBookVendor(1L, 5L, bookRequest)).isEqualTo(vendorResponse);
//    }
//
//    @Test
//    @DisplayName("Test for delete vendor by id")
//    void deleteBookVendor() {
//        vendorResponse.setBookList(List.of(book1));
//
//        doReturn(Optional.of(vendor)).when(repository).findById(1L);
//        doReturn(Optional.of(book1)).when(bookRepository).findById(5L);
//
//        doNothing().when(bookRepository).deleteById(5L);
//        doReturn(vendor).when(repository).save(vendor);
//        doReturn(vendorResponse).when(viewMapper).viewVendor(vendor);
//
//        assertThat(vendorService.deleteBookVendor(1L, 5L).getBookList().size()).isEqualTo(1);
//    }
//
//    @Nested
//    @DisplayName("Test for promo-code")
//    @Tag("promocode")
//    class PromocodeTest {
//        PromocodeRequest promocodeRequest = PromocodeRequest.builder()
//                .promoName("promo1")
//                .amountOfPromo(10)
//                .startingDay(LocalDate.of(2022, 7, 12))
//                .finishingDay(LocalDate.of(2022, 8, 12))
//                .build();
//        Promocode promocode = Promocode.builder()
//                .id(10L)
//                .promoName("promo1")
//                .amountOfPromo(10)
//                .startingDay(LocalDate.of(2022, 7, 12))
//                .finishingDay(LocalDate.of(2022, 8, 12))
//                .user(vendor)
//                .build();
//
//
//        @Test
//        void addPromoCode() {
//            vendorResponse.setPromocodes(List.of(promocode));
//            doReturn(promocode).when(promocodeEditMapper).register(promocodeRequest);
//            doReturn(Optional.of(vendor)).when(repository).findById(1L);
//            doReturn(promocode).when(promocodeRepository).save(promocode);
//            doReturn(vendor).when(repository).save(vendor);
//            doReturn(vendorResponse).when(viewMapper).viewVendor(vendor);
//
//            assertThat(vendorService.addPromoCode(promocodeRequest, 1L).getPromocodes()).isEqualTo(vendorResponse.getPromocodes());
//        }
//
//        @Test
//        void updatePromoCode() {
//            doReturn(Optional.of(vendor)).when(repository).findById(1L);
//            doReturn(Optional.of(promocode)).when(promocodeRepository).findById(10L);
//            doReturn(promocode).when(promocodeEditMapper).update(promocode, promocodeRequest);
//
//            promocode.setPromoName("promo2");
//            book1.setPromocode(promocode);
//            promocode.setUser(vendor);
//            assertThat(promocodeEditMapper.update(promocode, promocodeRequest).getPromoName()).isEqualTo("promo2");
//            doReturn(promocode).when(promocodeRepository).save(promocode);
//
//
//            doReturn(vendor).when(repository).save(vendor);
//            vendorResponse.setPromocodes(List.of(promocode));
//            doReturn(vendorResponse).when(viewMapper).viewVendor(vendor);
//
//            var actual = vendorService.updatePromoCode(promocodeRequest, 1L, 10L);
//
//            assertThat(actual.getPromocodes()).isEqualTo(vendorResponse.getPromocodes());
//        }
//
//        @Test
//        void deletePromoCode() {
//            Promocode promocode = Promocode.builder()
//                    .id(10L)
//                    .promoName("promo1")
//                    .amountOfPromo(10)
//                    .startingDay(LocalDate.of(2022, 7, 12))
//                    .finishingDay(LocalDate.of(2022, 8, 12))
//                    .user(vendor)
//                    .books(List.of(book1))
//                    .build();
//            Promocode promocode2 = Promocode.builder()
//                    .id(11L)
//                    .promoName("promo11")
//                    .amountOfPromo(10)
//                    .startingDay(LocalDate.of(2022, 7, 12))
//                    .finishingDay(LocalDate.of(2022, 8, 12))
//                    .user(vendor)
//                    .books(List.of(book1))
//                    .build();
//            vendor.setPromocode(List.of(promocode, promocode2));
//            vendorResponse.setPromocodes(List.of(promocode));
//
//            doReturn(Optional.of(vendor)).when(repository).findById(1L);
//
//            doReturn(Optional.of(promocode)).when(promocodeRepository).findById(10L);
//            promocodeRepository.delete(promocode);
//            verify(promocodeRepository, times(1)).delete(promocode);
//
//            doReturn(vendor).when(repository).save(vendor);
//            doReturn(vendorResponse).when(viewMapper).viewVendor(vendor);
//
//            assertThat(vendorService.deletePromoCode(1L, 10L).getPromocodes().size()).isOne();
//        }
//    }
//}