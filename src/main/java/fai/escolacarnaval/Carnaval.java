package fai.escolacarnaval;

import java.util.*;

class EscolaDeSamba {
    private String nome;
    private String sambaEnredo;
    private String tema;
    private List<String> caracteristicas;
    private List<String> famosos;

    public EscolaDeSamba(String nome, String sambaEnredo, String tema) {
        this.nome = nome;
        this.sambaEnredo = sambaEnredo;
        this.tema = tema;
        this.caracteristicas = new ArrayList<>();
        this.famosos = new ArrayList<>();
    }

    public void adicionarCaracteristica(String caracteristica) {
        if (!caracteristica.trim().isEmpty()) {
            caracteristicas.add(caracteristica);
        }
    }

    public void adicionarFamoso(String famoso) {
        if (!famoso.trim().isEmpty()) {
            famosos.add(famoso);
        }
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EscolaDeSamba that = (EscolaDeSamba) obj;
        return nome.equalsIgnoreCase(that.nome);
    }

    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return "EscolaDeSamba{" +
                "nome='" + nome + '\'' +
                ", sambaEnredo='" + sambaEnredo + '\'' +
                ", tema='" + tema + '\'' +
                ", caracteristicas=" + caracteristicas +
                ", famosos=" + famosos +
                '}';
    }
}

class GerenciadorNotas {
    private Map<String, List<Double>> notas;

    public GerenciadorNotas() {
        this.notas = new HashMap<>();
    }

    public void adicionarNotas(String escola, List<Double> novasNotas) {
        notas.computeIfAbsent(escola, k -> new ArrayList<>()).addAll(novasNotas);
    }

    public double calcularMedia(String escola) {
        List<Double> notasEscola = notas.get(escola);
        if (notasEscola == null || notasEscola.isEmpty()) {
            return 0.0;
        }
        return notasEscola.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public String getEscolaCampea() {
        return notas.entrySet().stream()
                .max(Comparator.comparingDouble(e -> calcularMedia(e.getKey())))
                .map(Map.Entry::getKey)
                .orElse("Nenhuma escola cadastrada");
    }
}

public class Carnaval {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<EscolaDeSamba> escolas = new ArrayList<>();
        GerenciadorNotas gerenciador = new GerenciadorNotas();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar escola de samba");
            System.out.println("2. Adicionar notas a uma escola");
            System.out.println("3. Ver escola campeã");
            System.out.println("4. Sair");
            int opcao = lerNumero("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarEscola(escolas);
                    break;
                case 2:
                    adicionarNotas(escolas, gerenciador);
                    break;
                case 3:
                    System.out.println("A escola campeã é: " + gerenciador.getEscolaCampea());
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static int lerNumero(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("O campo não pode ficar vazio. Tente novamente.");
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    private static void cadastrarEscola(List<EscolaDeSamba> escolas) {
        String nome = lerTexto("Digite o nome da escola: ");
        String sambaEnredo = lerTexto("Digite o samba-enredo: ");
        String tema = lerTexto("Digite o tema: ");

        EscolaDeSamba escola = new EscolaDeSamba(nome, sambaEnredo, tema);
        int numCaracteristicas = lerNumero("Quantas características deseja adicionar? ");

        for (int i = 0; i < numCaracteristicas; i++) {
            escola.adicionarCaracteristica(lerTexto("Digite a característica " + (i + 1) + ": "));
        }

        if (lerTexto("Tem famosos participando? (S/N): ").equalsIgnoreCase("S")) {
            int numFamosos = lerNumero("Quantos famosos deseja adicionar? ");
            for (int i = 0; i < numFamosos; i++) {
                escola.adicionarFamoso(lerTexto("Digite o nome do famoso " + (i + 1) + ": "));
            }
        }

        escolas.add(escola);
        System.out.println("Escola cadastrada com sucesso!");
    }

    private static void adicionarNotas(List<EscolaDeSamba> escolas, GerenciadorNotas gerenciador) {
        if (escolas.isEmpty()) {
            System.out.println("Nenhuma escola cadastrada ainda!");
            return;
        }

        for (int i = 0; i < escolas.size(); i++) {
            System.out.println((i + 1) + ". " + escolas.get(i).getNome());
        }

        int escolha = lerNumero("Escolha o número da escola para adicionar notas: ");
        if (escolha < 1 || escolha > escolas.size()) {
            System.out.println("Escolha inválida!");
            return;
        }

        EscolaDeSamba escolaEscolhida = escolas.get(escolha - 1);
        int numNotas = lerNumero("Quantas notas deseja adicionar? ");

        List<Double> notas = new ArrayList<>();
        for (int i = 0; i < numNotas; i++) {
            notas.add((double) lerNumero("Digite a nota " + (i + 1) + ": "));
        }

        gerenciador.adicionarNotas(escolaEscolhida.getNome(), notas);
        System.out.println("Notas adicionadas com sucesso!");
    }
}
