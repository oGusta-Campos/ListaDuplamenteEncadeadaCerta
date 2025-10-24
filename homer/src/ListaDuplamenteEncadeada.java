import java.util.Scanner; 

class No {
    String valor;
    No anterior;
    No proximo;

    public No(String valor) {
        this.valor = valor;
        this.anterior = null;
        this.proximo = null;
    }
}

public class ListaDuplamenteEncadeada {
    private No inicio;
    private No fim;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    // Inserir no final
    public void inserir(String valor) {
        No novoNo = new No(valor);

        if (inicio == null) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            fim.proximo = novoNo;
            novoNo.anterior = fim;
            fim = novoNo;
        }
        tamanho++;
    }

    // Inserir no início
    public void inserirNoInicio(String valor) {
        No novoNo = new No(valor);
        if (inicio == null) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            novoNo.proximo = inicio;
            inicio.anterior = novoNo;
            inicio = novoNo;
        }
        tamanho++;
        System.out.println("✅ \"" + valor + "\" inserido no início da lista.");
    }

    // Inserir em posição específica
    public void inserirNaPosicao(String valor, int posicao) {
        if (posicao < 0 || posicao > tamanho) {
            System.out.println("❌ Posição inválida!");
            return;
        }

        if (posicao == 0) {
            inserirNoInicio(valor);
            return;
        } else if (posicao == tamanho) {
            inserir(valor);
            return;
        }

        No novoNo = new No(valor);
        No atual = inicio;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proximo;
        }

        novoNo.anterior = atual.anterior;
        novoNo.proximo = atual;
        atual.anterior.proximo = novoNo;
        atual.anterior = novoNo;
        tamanho++;
        System.out.println("✅ \"" + valor + "\" inserido na posição " + posicao + ".");
    }

    // Remover por nome
    public void remover(String valor) {
        No atual = inicio;
        while (atual != null) {
            if (atual.valor.equalsIgnoreCase(valor)) {
                if (atual.anterior != null) {
                    atual.anterior.proximo = atual.proximo;
                } else {
                    inicio = atual.proximo;
                }

                if (atual.proximo != null) {
                    atual.proximo.anterior = atual.anterior;
                } else {
                    fim = atual.anterior;
                }

                tamanho--;
                System.out.println("✅ \"" + valor + "\" removido da lista.");
                return;
            }
            atual = atual.proximo;
        }
        System.out.println("❌ Valor não encontrado.");
    }

    // Remover por posição
    public void removerPorPosicao(int posicao) {
        if (posicao < 0 || posicao >= tamanho) {
            System.out.println("❌ Posição inválida!");
            return;
        }

        if (posicao == 0) {
            removerDoInicio();
            return;
        } else if (posicao == tamanho - 1) {
            removerDoFinal();
            return;
        }

        No atual = inicio;
        for (int i = 0; i < posicao; i++) {
            atual = atual.proximo;
        }

        System.out.println("✅ \"" + atual.valor + "\" removido da posição " + posicao + ".");
        atual.anterior.proximo = atual.proximo;
        atual.proximo.anterior = atual.anterior;
        tamanho--;
    }

    // Remover do início
    public void removerDoInicio() {
        if (inicio == null) {
            System.out.println("❌ Lista já está vazia!");
            return;
        }

        System.out.println("✅ \"" + inicio.valor + "\" removido do início da lista.");
        inicio = inicio.proximo;

        if (inicio != null) {
            inicio.anterior = null;
        } else {
            fim = null; // lista ficou vazia
        }

        tamanho--;
    }

    // Remover do final
    public void removerDoFinal() {
        if (fim == null) {
            System.out.println("❌ Lista já está vazia!");
            return;
        }
        System.out.println("✅ \"" + fim.valor + "\" removido do final da lista.");
        if (inicio == fim) {
            inicio = null;
            fim = null;
        } else {
            fim = fim.anterior;
            fim.proximo = null;
        }
        tamanho--;
    }

    // Esvaziar lista
    public void esvaziar() {
        inicio = null;
        fim = null;
        tamanho = 0;
        System.out.println("🧹 Lista esvaziada!");
    }

    // Exibir lista
    public void exibir() {
        No atual = inicio;
        if (atual == null) {
            System.out.println("[Lista vazia]");
            return;
        }

        System.out.print("📋 Lista: ");
        while (atual != null) {
            System.out.print(atual.valor);
            if (atual.proximo != null) System.out.print(" <-> ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    // Retornar tamanho
    public int getTamanho() {
        return tamanho;
    }

    // Verificar se contém
    public boolean contem(String valor) {
        No atual = inicio;
        while (atual != null) {
            if (atual.valor.equalsIgnoreCase(valor)) return true;
            atual = atual.proximo;
        }
        return false;
    }

    // MENU
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
        int opcao;

        do {
            System.out.println("\n====== MENU LISTA DUPLAMENTE ENCADEADA ======");
            System.out.println("1 - Inserir no final");
            System.out.println("2 - Inserir no início");
            System.out.println("3 - Inserir em posição específica");
            System.out.println("4 - Remover por nome");
            System.out.println("5 - Remover por posição");
            System.out.println("6 - Remover do final");
            System.out.println("7 - Remover do início");
            System.out.println("8 - Exibir lista");
            System.out.println("9 - Esvaziar lista");
            System.out.println("10 - Verificar se nome está na lista");
            System.out.println("11 - Mostrar tamanho da lista");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome para inserir no final: ");
                    lista.inserir(sc.nextLine());
                    break;

                case 2:
                    System.out.print("Digite o nome para inserir no início: ");
                    lista.inserirNoInicio(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Digite o nome: ");
                    String nomePos = sc.nextLine();
                    System.out.print("Digite a posição: ");
                    int pos = sc.nextInt();
                    sc.nextLine();
                    lista.inserirNaPosicao(nomePos, pos);
                    break;

                case 4:
                    System.out.print("Digite o nome a remover: ");
                    lista.remover(sc.nextLine());
                    break;

                case 5:
                    System.out.print("Digite a posição a remover: ");
                    int posRem = sc.nextInt();
                    sc.nextLine();
                    lista.removerPorPosicao(posRem);
                    break;

                case 6:
                    lista.removerDoFinal();
                    break;

                case 7:
                    lista.removerDoInicio();
                    break;

                case 8:
                    lista.exibir();
                    break;

                case 9:
                    lista.esvaziar();
                    break;

                case 10:
                    System.out.print("Digite o nome para buscar: ");
                    String nomeBuscar = sc.nextLine();
                    System.out.println(lista.contem(nomeBuscar)
                            ? "✅ O nome está na lista."
                            : "❌ O nome não está na lista.");
                    break;

                case 11:
                    System.out.println("📏 Tamanho da lista: " + lista.getTamanho());
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("❌ Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
