package br.edu.up.app

import android.app.Application
import android.content.Context
import br.edu.up.app.data.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.firestore.ktx.memoryCacheSettings
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@HiltAndroidApp
@InstallIn(SingletonComponent::class)
class AppBase : Application() {

    @Provides
    fun provideProdutosRef() : CollectionReference {
        val firestore = Firebase.firestore
        val settings = firestoreSettings {
            setLocalCacheSettings(memoryCacheSettings {  })
        }
        firestore.firestoreSettings = settings
        return firestore.collection("produtos")
    }

    @Provides
    fun provideProdutoRepositoryFirebase(produtosRef: CollectionReference)
             : ProdutoRepositoryFirebase{
        return ProdutoRepositoryFirebase(produtosRef)
    }

    @Provides
    fun provideProdutoRepositorySqlite(produtoDAO: ProdutoDAO)
             : ProdutoRepository {
        return ProdutoRepositorySqlite(produtoDAO)
    }

    @Provides
    fun provideProdutoDAO(bancoSQLite: BancoSQLite): ProdutoDAO {
        return bancoSQLite.produtoDao()
    }

    @Provides
    @Singleton
    fun provideBanco(@ApplicationContext ctx: Context): BancoSQLite {
        return BancoSQLite.get(ctx)
    }
}