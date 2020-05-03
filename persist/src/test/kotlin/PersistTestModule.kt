import com.google.inject.Guice
import com.google.inject.Injector
import com.moneytree.persist.PersistModules

open class PersistTestModule {
    val injector: Injector = Guice.createInjector(
        PersistModules("test")
    )
}
