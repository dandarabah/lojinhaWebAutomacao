package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.ListaDeProdutosPage;
import paginas.LoginPage;
import paginas.RemoverProdutoDaListaPage;

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
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navegar para a pagina da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2");

    }

    @Test
    @DisplayName("Nao e permitido regitrar um produto com valor igual a zero")
    public void testNaoEPermitoRegistrarProdutoComValorIgualAZero() {

        //Fazer o login
        String mensagemToastApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Vou para a tela de registro de produto
                .acessarFormularioDeAdicaoNovoProduto()
                 //Vou preencher dados do produto e o valor sera igual a zero
                .informarNomeDoProduto("Mouse sem fio")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("branco, cinza, preto")
                //Preenchimento do fomulario
                .submeterFormularioDeAdicaoComErro()
                //Validar mensagem
                .capturarMensagemApresentada();

        //Validar a mensagem que foi enviada para o Toast
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToastApresentada);
    }

    @Test
    @DisplayName("Nao permitir registrar produto maior que R$7000")
    public void testNaoPermitirRegistrarProdutoMaiorQueR$7000(){

        //Fazer o login
        String mensagemToastApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Vou para a tela de registro de produto
                .acessarFormularioDeAdicaoNovoProduto()
                //Vou preencher dados do produto e o valor sera igual a zero
                .informarNomeDoProduto("Macbook")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("branco")
                //Preenchimento do fomulario
                .submeterFormularioDeAdicaoComErro()
                //Validar mensagem
                .capturarMensagemApresentada();

        //Validar a mensagem que foi enviada para o Toast
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToastApresentada);
    }

    @Test
    @DisplayName("Permitir registrar produto a partir de R$0.01")
    public void testPermitirRegistrarProdutoAPartirDeR$001(){
        //Fazer o login
        String mensagemToastApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Vou para a tela de registro de produto
                .acessarFormularioDeAdicaoNovoProduto()
                //Vou preencher dados do produto e o valor sera igual a zero
                .informarNomeDoProduto("mouse esfera14")
                .informarValorDoProduto("001")
                .informarCoresDoProduto("branco, preto")
                //Preenchimento do fomulario
                .submeterFormularioDeAdicaoComErro()
                //Validar mensagem
                .capturarMensagemApresentada();

        //Validar a mensagem que foi enviada para o Toast
        Assertions.assertEquals("Produto adicionado com sucesso", mensagemToastApresentada);
    }


    @Test
    @DisplayName("Permitir registrar produto no limite maximo de R$7000")
    public void testPermitirRegistrarProdutoNoLimiteMaximoDeR$7000(){
        //Fazer o login
        String mensagemToastApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Vou para a tela de registro de produto
                .acessarFormularioDeAdicaoNovoProduto()
                //Vou preencher dados do produto e o valor sera igual a zero
                .informarNomeDoProduto("Playstation25")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("branco, preto")
                //Preenchimento do formulario
                .submeterFormularioDeAdicaoComErro()
                //Validar mensagem
                .capturarMensagemApresentada();

        //Validar a mensagem que foi enviada para o Toast
        Assertions.assertEquals("Produto adicionado com sucesso", mensagemToastApresentada);
    }

    @Test
    @DisplayName("Permitir editar produto")
    public void testPermitirEditarProduto(){
    //Fazer o login
        String mensagemToastApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Acessar produto da lista para edição
                .acessarFormularioDeEdicaoNovoProduto("SamSam")
                //Na tela de Edição alterar os campos desejados
                .informarEditarNomeDoProduto("Cabo SamSam")
                .informarEditarValorDoProduto("4000")
                .informarEditarCoresDoProduto("vermelho")
                //Preenchimento do Formulario
                .submeterFormularioEdicao()
                //Retorna para lista de produtos
                .submeterListaDeProdutoEditada()
                //Validar mensagem
                .capturarMensagemApresentada();

        //Validar a mensagem que foi enviada para o Toast
        Assertions.assertEquals("Produto alterado com sucesso", mensagemToastApresentada);
    }


    @Test
    @DisplayName("Permitir adicionar componente no produto")
    public void testPermitirAdicionarComponenteNoProduto(){
    //Fazer o login
        String mensagemToastApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Acessar produto da lista para edição
                .acessarFormularioDeEdicaoNovoProduto("notebook lex")
                //Adicionar Componente ao Produto
                .acessarFormularioAdicionarNovoComponenteProduto()
                .informarNomeDoComponenteProduto("carregador, capa de proteção")
                .informarQuantidadeDeComponentesProduto("2")
                //Salvar e retorna para a tela de Edição Produto
                .submeterFormularioDeEdicaoComponente()
                //Salvar as alterações do formulario
                .submeterFormularioEdicao()
                //Retorna para lista de produtos
                .submeterListaDeProdutoEditada()
                //Validar mensagem
                .capturarMensagemApresentada();

        //Validar a mensagem que foi enviada para o Toast
        Assertions.assertEquals("Componente de produto adicionado com sucesso", mensagemToastApresentada);
        Assertions.assertEquals("Produto alterado com sucesso", mensagemToastApresentada);
    }

    @Test
    @DisplayName("Excluir um item da lista de produto")
    public void testExcluirUmItemDaListaDeProdutos(){

        ListaDeProdutosPage listaDeProdutosPage = new ListaDeProdutosPage(navegador);

        //Fazer o login
        String mensagemToastApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                //Acessar produto da lista para edição
               .acessarFormularioDeEdicaoNovoProduto("Caixa Organizadora").toString();
        //Remover produto da lista
        RemoverProdutoDaListaPage removerProdutoDaListaPage =
        listaDeProdutosPage.getRemoverProdutoDaLista().excluirUmProdutoDaLista("Caixa organizadora");
        //Validar mensagem
        listaDeProdutosPage.capturarMensagemApresentada();

        Assertions.assertEquals("Produto removido com sucesso", mensagemToastApresentada);

    }
    @AfterEach
    public void afterEach(){
        //Fechar o navegador
        navegador.quit();
    }
}
