package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");
        this.navegador = new ChromeDriver();
        
        //Vou maximizar a tela
        this.navegador.manage().window().maximize();

        //Vou definir um tempo de espera padrao de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // Navegar para a pagina da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2");

    }
    @Test
    @DisplayName("Nao e permitido regitrar um produto com valor igual a zero")
    public void testNaoEPermitoRegistrarProdutoComValorIgualAZero() {

        //Paginas
        String mensagemToastApresentada = new LoginPage(navegador) //A mensagem que está no métdo "capturaMensagemApresentada" é exibida
                //Fazer o login
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Vou para a tela de registro de produto
                .acessarFormularioAdicaoNovoProduto()
                //Vou preencher dados do produto e o valor sera igual a zero
                .informarNomeDoProduto("Tablet")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("preto, branco, rosa")
                //Vou submeter o formulario
                .submeterFormularioDeAdicaoComErro()
                //Validar mensagem
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToastApresentada);
    }

    @Test
    @DisplayName("Nao e permitido registrar um produto com valor acima de 7000,01")
    public void testNaoEPermitidoRegistrarProdutoComValorAcimaDe7000(){

        //Paginas
        String mensagemToastApresentada = new LoginPage(navegador) //A mensagem que está no métdo "capturaMensagemApresentada" é exibida
                //Fazer o login
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Vou para a tela de registro de produto
                .acessarFormularioAdicaoNovoProduto()
                //Vou preencher dados do produto e o valor sera igual a R$7000,01
                .informarNomeDoProduto("Xbox")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("preto")
                //Vou submeter o formulario
                .submeterFormularioDeAdicaoComErro()
                //Validar mensagem
                .capturarMensagemApresentada();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToastApresentada);
    }

    @Test
    @DisplayName("Permitir Registrar Produto Dentro Do Valor Limite")
    public void testPermitirRegistrarProdutoDentroDoValorLimite(){

        String mensagemToastApresentada = new LoginPage(navegador) //A mensagem que está no métdo "capturaMensagemApresentada" é exibida
                //Fazer o login
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Vou para a tela de registro de produto
                .acessarFormularioAdicaoNovoProduto()
                //Vou preencher dados do produto e com valor dentro do limite
                .informarNomeDoProduto("Notebook ABC")
                .informarValorDoProduto("250000")
                .informarCoresDoProduto("preto")
                //Adicionar produto com sucesso
                .submeterFormularioDeAdicaoComSucesso()
                //Validar mensagem
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso",mensagemToastApresentada);

    }







        @AfterEach
        public void afterEach() {
        //Vou fechar o navegador
        navegador.quit();
    }
}
