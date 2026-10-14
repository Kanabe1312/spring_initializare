package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.time.Clock;
import java.util.Arrays;
import java.util.Set;

@Component
public class DemoRunner implements CommandLineRunner {

    private static final Set<String> STEREOTIPURI = Set.of(
            "Component", "Service", "Repository", "Configuration", "SpringBootApplication", "Primary");

    private final ApplicationContext context;
    private final BookRepository repository;
    private final BookService bookService;
    private final BookReport bookReport;
    private final Clock clock;

    public DemoRunner(ApplicationContext context,
                      BookRepository repository,
                      BookService bookService,
                      BookReport bookReport,
                      Clock clock) {
        this.context = context;
        this.repository = repository;
        this.bookService = bookService;
        this.bookReport = bookReport;
        this.clock = clock;
    }

    @Override
    public void run(String... args) {
        line();
        title("SPRING FUNDAMENTALS — DEMO: BIBLIOTECA (@Component)");
        line();
        System.out.println();
        System.out.println("Tot ce ai văzut deasupra s-a întâmplat ÎNAINTE de linia asta: Spring a scanat pachetul,");
        System.out.println("a chemat constructorii, a rulat @PostConstruct. Abia acum rulează codul nostru.");

        section("1) Component scan — ce a găsit Spring în com.example.demo");
        for (String name : context.getBeanDefinitionNames()) {
            Class<?> type = context.getType(name);
            if (type == null) {
                continue;
            }
            Class<?> userClass = ClassUtils.getUserClass(type);
            if (!userClass.getPackageName().equals("com.example.demo")) {
                continue;
            }
            System.out.println("   bean '" + name + "' → " + userClass.getSimpleName() + "  " + stereotipuri(userClass));
        }
        System.out.println("   bean 'clock' → Clock  (publicat de ClockConfig prin @Bean, clasă JDK)");
        System.out.println();
        System.out.println("   PaperBookPrinter e în același pachet, dar N-ARE nicio adnotare:");
        System.out.println("   context.getBeanNamesForType(PaperBookPrinter.class).length = "
                + context.getBeanNamesForType(PaperBookPrinter.class).length);
        System.out.println("   → Spring nu știe de ea. Dacă o ceri într-un constructor, aplicația NU pornește:");
        System.out.println("     \"Parameter 0 of constructor in ... required a bean of type 'PaperBookPrinter' that could not be found.\"");
        System.out.println("   → @Component / @Service / @Repository înseamnă același lucru: „Spring, fă tu new la clasa asta”.");
        System.out.println("     Numele diferă doar ca să citești rolul clasei.");

        section("2) @Component = UN SINGUR new, făcut de Spring, dat tuturor");
        System.out.println("   InMemoryBookRepository.instancesCreated() = " + InMemoryBookRepository.instancesCreated());
        System.out.println("   repository == context.getBean(BookRepository.class) → "
                + (repository == context.getBean(BookRepository.class)));
        System.out.println("   DemoRunner, BookService și BookReport au cerut un BookRepository în constructor.");
        System.out.println("   Toți trei au primit " + repository + ". Dovada: adaug prin service, citesc prin report.");
        Book added = bookService.addBook("Java Concurrency in Practice", "Brian Goetz");
        System.out.println("   bookService.addBook(...) → " + added);
        System.out.println("   bookReport.summary()    → " + bookReport.summary());
        for (String l : bookReport.lines()) {
            System.out.println("      " + l);
        }

        section("3) FĂRĂ Spring — același cod, cu new de mână");
        BookService manualService = new BookService(new InMemoryBookRepository(), clock, "Biblioteca de mână");
        BookReport manualReport = new BookReport(new InMemoryBookRepository(), new PlainBookFormatter());
        manualService.addBook("Refactoring", "Martin Fowler");
        System.out.println("   manualService.addBook(\"Refactoring\") → apoi manualReport.summary() = " + manualReport.summary());
        System.out.println("   InMemoryBookRepository.instancesCreated() = " + InMemoryBookRepository.instancesCreated());
        System.out.println("   → Aceiași constructori, aceleași mesaje, dar le-a chemat codul nostru, nu Spring. Adnotarea nu schimbă clasa;");
        System.out.println("     ea doar îi spune lui Spring „pe asta o construiești tu”.");
        System.out.println("   → Două depozite diferite (#2 și #3): cartea adăugată într-unul nu există în celălalt.");
        System.out.println("   → Niciunul n-are cele 3 cărți din seed(): @PostConstruct rulează DOAR pe bean-urile create de Spring.");
        System.out.println("   → Fixul clasic de mână e Singleton-ul (getInstance()). Cu @Component nu-l mai scrii:");
        System.out.println("     containerul ține instanța unică și o dă oricui o cere prin constructor.");

        section("4) Celelalte adnotări din demo, pe scurt");
        System.out.println("   @Value        → bookService.libraryName() = \"" + bookService.libraryName() + "\" (din application.yaml)");
        System.out.println("   @Bean         → clock.getZone() = " + clock.getZone() + " (Clock e clasă JDK, nu poți pune @Component pe ea)");
        System.out.println("   @Primary      → BookFormatter are 2 implementări " + Arrays.toString(context.getBeanNamesForType(BookFormatter.class))
                + "; BookReport a primit " + context.getBean(BookFormatter.class).getClass().getSimpleName());
        System.out.println("   @PostConstruct→ seed() a pus 3 cărți după constructor, înainte ca cineva să folosească depozitul");

        System.out.println();
        line();
        title("DEMO COMPLETE");
        line();
    }

    private String stereotipuri(Class<?> cls) {
        return Arrays.stream(cls.getAnnotations())
                .map(a -> a.annotationType().getSimpleName())
                .filter(STEREOTIPURI::contains)
                .map(n -> "@" + n)
                .reduce((a, b) -> a + " " + b)
                .orElse("(fără adnotare)");
    }

    private void line() {
        System.out.println("====================================================");
    }

    private void title(String t) {
        System.out.println("  " + t);
    }

    private void section(String name) {
        System.out.println();
        System.out.println("── " + name + " ──");
    }
}
