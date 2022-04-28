# Receitas
![Web_Photo_Editor](https://user-images.githubusercontent.com/80295127/165016298-05853265-3653-44dd-a52c-29735f90a560.png)

## Sobre este projeto:

<p>A ideia deste aplicativo é: <em>"Cadastrar e listar receitas de forma persistente."</em></p>
<p><strong>Obs:</strong> O tema de <em>"receitas"</em> foi apenas o modelo escolhido por mim para implementar neste projeto, todo o código contido neste aplicativo pode ser reutilizado em qual quer outra aplicação que tenha uma listagem de itens com animações, uma tela de cadastro de itens com validações e persistência de dados com Room.</p>

## Motivação:
<p>Este aplicativo faz parte do meu portifólio pessoal e eu o desenvolvi visando os meus estudos e a prática dos conhecimentos adquiridos. Caso você faça o download deste repositório e observe pontos de melhoria, seja no código, estrutura, UI/UX, etc... Ou queira apenas dar um simples feedback eu ficarei extremamente feliz. Estou aqui para aprender e me tornar um desenvolvedor cada vez melhor. 😁</p>

<p>Você pode entrar em contato comigo através do <a href="https://www.linkedin.com/in/nunes1909/">Linkedin</a> e também através do e-mail: <a href="mailto:gnunes1909@gmail.com">gnunes1909@gmail.com</a></p>

## Algumas observações sobre o aplicativo:

1. Ainda não existe a funcionalidade de Login e Registro neste projeto, a tela inicial atualmente é a tela da Lista de Receitas.
2. Ainda não existe a funcionalidade de comunicação com uma API Web afim de manter os dados salvos na núvem.
3. Atualmente as receitas ficam salvas somente na memória através do Room.

## Funcionalidades:

- RecyclerView com animações:
    - Deslizar um item para excluir.
    - Mover um item para alterar de posição.
- Tela de cadastro:
    - Com validações e obrigatorieadade de campo.
    - FloatingActionButton para salvar e limpar o formulário.
- Menu para apagar todos itens da RecyclerView.
- Spinner com o "tipo" da receita. Cada tipo possui um ícone diferente na RecyclerView.

## Formas de acessar o app:
1. Clonando o repositório:
    - ``` git clone https://github.com/nunes1909/receitas-java.git ```
3. Efetuando o download do Apk:
    - V1 - Sem Room: <a href="https://drive.google.com/file/d/1z5DsZ_5QHhmdH5a074gdV5IwUuq5W3mo/view?usp=sharing">Neste link.</a>
    - V2 - Com Room: <a href="https://drive.google.com/file/d/1mwgDB6C8Ltnel6-A2YN_8z0Xudj4t9cm/view?usp=sharing">Neste link.</a>

## Metas para médio e longo prazo:
1. Implementar Cadastro e Login.
2. Implementar Relacionamento entre Entidades (Receita e Ingredientes) e AsyncTasks.
3. Implementar comunicação com API Web.
4. Implementar funcionalidades e componentes de Arquitetura Android.
