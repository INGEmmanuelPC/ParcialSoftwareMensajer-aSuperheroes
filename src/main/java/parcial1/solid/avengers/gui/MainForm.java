package parcial1.solid.avengers.gui;

import parcial1.solid.avengers.heroes.*;
import parcial1.solid.avengers.misiones.AsignadorMisiones;
import parcial1.solid.avengers.misiones.Mision;
import parcial1.solid.avengers.misiones.ResultadoAsignacion;
import parcial1.solid.avengers.misiones.TipoHabilidad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Interfaz gráfica principal del sistema de asignación de misiones.
 *
 * <p>Permite al usuario crear héroes personalizados, crear misiones
 * y ejecutar la asignación automática. Los héroes predefinidos
 * (Aquaman, Thor, SpiderMan, IronMan, Hulk) se cargan al iniciar.</p>
 *
 * <p>La interfaz se construye programáticamente con Swing puro,
 * sin depender del plugin Swing UI Designer ni de archivos {@code .form}.</p>
 *
 * <p>Esta clase se encarga <strong>únicamente</strong> de la presentación
 * (SRP). Toda la lógica de negocio se delega al {@link AsignadorMisiones}.</p>
 *
 * @author Equipo Avengers
 * @version 2.0
 */
public class MainForm extends JFrame {

    // ── Componentes del panel de héroe ──
    private JTextField txtNombreHeroe;
    private JCheckBox chkAcuatico;
    private JCheckBox chkVolador;
    private JCheckBox chkTrepamuros;
    private JCheckBox chkSigiloso;
    private JCheckBox chkControlFuego;
    private JButton btnCrearHeroe;

    // ── Componentes del panel de misión ──
    private JTextField txtNombreMision;
    private JCheckBox chkMisionAcuatico;
    private JCheckBox chkMisionVolador;
    private JCheckBox chkMisionTrepamuros;
    private JCheckBox chkMisionSigiloso;
    private JCheckBox chkMisionControlFuego;
    private JButton btnCrearMision;

    // ── Componentes de visualización ──
    private JButton btnAsignar;
    private JTextArea txtResultados;
    private JList<String> listRegistro;
    private DefaultListModel<String> modeloRegistro;

    // ── Estado interno ──
    /** Lista de héroes disponibles en el sistema. */
    private final List<SuperHeroe> listaHeroes;

    /** Lista de misiones creadas en el sistema. */
    private final List<Mision> listaMisiones;

    /** Servicio de asignación de misiones. */
    private final AsignadorMisiones asignador;

    /**
     * Crea y muestra la ventana principal del sistema.
     *
     * @param asignador servicio de asignación inyectado desde la configuración.
     */
    public MainForm(AsignadorMisiones asignador) {
        this.asignador = asignador;
        this.listaHeroes = new ArrayList<>();
        this.listaMisiones = new ArrayList<>();
        this.modeloRegistro = new DefaultListModel<>();

        configurarVentana();
        construirInterfaz();
        conectarEventos();
        cargarHeroesPredefinidos();
    }

    /**
     * Configura las propiedades básicas de la ventana.
     */
    private void configurarVentana() {
        setTitle("Sistema de Asignación de Misiones - Avengers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 720);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
    }

    /**
     * Construye toda la disposición de la interfaz gráfica.
     */
    private void construirInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(12, 12, 12, 12));

        // ── Título superior ──
        JLabel titulo = new JLabel(
                "Sistema de Asignación de Misiones",
                SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(new EmptyBorder(5, 0, 10, 0));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // ── Panel central: Hero Creation | Mission Creation ──
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 12, 0));
        panelCentral.add(crearPanelHeroe());
        panelCentral.add(crearPanelMision());
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // ── Panel inferior: lista, botón, resultados ──
        panelPrincipal.add(crearPanelInferior(), BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    /**
     * Crea el panel de creación de héroes.
     *
     * @return el panel configurado.
     */
    private JPanel crearPanelHeroe() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Hero Creation",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14)));

        // Nombre
        JPanel filaNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filaNombre.add(new JLabel("Nombre:"));
        txtNombreHeroe = new JTextField(15);
        filaNombre.add(txtNombreHeroe);
        panel.add(filaNombre);

        // Habilidades
        JPanel panelHab = new JPanel();
        panelHab.setLayout(new BoxLayout(panelHab, BoxLayout.Y_AXIS));
        panelHab.setBorder(BorderFactory.createTitledBorder("Habilidades"));

        chkAcuatico = new JCheckBox("Acuático");
        chkVolador = new JCheckBox("Volador");
        chkTrepamuros = new JCheckBox("Trepamuros");
        chkSigiloso = new JCheckBox("Sigiloso");
        chkControlFuego = new JCheckBox("Control de Fuego");

        panelHab.add(chkAcuatico);
        panelHab.add(chkVolador);
        panelHab.add(chkTrepamuros);
        panelHab.add(chkSigiloso);
        panelHab.add(chkControlFuego);
        panel.add(panelHab);

        // Botón
        btnCrearHeroe = new JButton("Crear Héroe");
        btnCrearHeroe.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(5));
        panel.add(btnCrearHeroe);

        return panel;
    }

    /**
     * Crea el panel de creación de misiones.
     *
     * @return el panel configurado.
     */
    private JPanel crearPanelMision() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Mission Creation",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14)));

        // Nombre
        JPanel filaNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filaNombre.add(new JLabel("Nombre:"));
        txtNombreMision = new JTextField(15);
        filaNombre.add(txtNombreMision);
        panel.add(filaNombre);

        // Habilidades requeridas
        JPanel panelHab = new JPanel();
        panelHab.setLayout(new BoxLayout(panelHab, BoxLayout.Y_AXIS));
        panelHab.setBorder(BorderFactory.createTitledBorder(
                "Habilidades Requeridas"));

        chkMisionAcuatico = new JCheckBox("Acuático");
        chkMisionVolador = new JCheckBox("Volador");
        chkMisionTrepamuros = new JCheckBox("Trepamuros");
        chkMisionSigiloso = new JCheckBox("Sigiloso");
        chkMisionControlFuego = new JCheckBox("Control de Fuego");

        panelHab.add(chkMisionAcuatico);
        panelHab.add(chkMisionVolador);
        panelHab.add(chkMisionTrepamuros);
        panelHab.add(chkMisionSigiloso);
        panelHab.add(chkMisionControlFuego);
        panel.add(panelHab);

        // Botón
        btnCrearMision = new JButton("Crear Misión");
        btnCrearMision.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(5));
        panel.add(btnCrearMision);

        return panel;
    }

    /**
     * Crea el panel inferior con la lista de registro, botón de
     * asignación y área de resultados.
     *
     * @return el panel configurado.
     */
    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        // ── JList de registro (héroes y misiones creados) ──
        listRegistro = new JList<>(modeloRegistro);
        JScrollPane scrollLista = new JScrollPane(listRegistro);
        scrollLista.setBorder(BorderFactory.createTitledBorder(
                "Héroes y Misiones Registrados"));
        scrollLista.setPreferredSize(new Dimension(0, 110));
        panel.add(scrollLista, BorderLayout.NORTH);

        // ── Botón de asignación automática ──
        btnAsignar = new JButton("Asignar Misiones Automáticamente");
        btnAsignar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAsignar.setBackground(new Color(34, 139, 34));
        btnAsignar.setForeground(Color.WHITE);
        btnAsignar.setFocusPainted(false);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(btnAsignar);
        panel.add(panelBoton, BorderLayout.CENTER);

        // ── Área de resultados ──
        txtResultados = new JTextArea(8, 50);
        txtResultados.setEditable(false);
        txtResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollResultados = new JScrollPane(txtResultados);
        scrollResultados.setBorder(BorderFactory.createTitledBorder(
                "Resultados de Asignación"));
        panel.add(scrollResultados, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Conecta los eventos de los botones a sus métodos correspondientes.
     */
    private void conectarEventos() {
        btnCrearHeroe.addActionListener(e -> crearHeroe());
        btnCrearMision.addActionListener(e -> crearMision());
        btnAsignar.addActionListener(e -> asignarMisionesAutomaticamente());
    }

    /**
     * Carga los héroes predefinidos del sistema al iniciar.
     */
    private void cargarHeroesPredefinidos() {
        agregarHeroe(new Aquaman());
        agregarHeroe(new SpiderMan());
        agregarHeroe(new Thor());
        agregarHeroe(new IronMan());
        agregarHeroe(new Hulk());

        txtResultados.setText(
                "Sistema iniciado con 5 héroes predefinidos.\n");
    }

    /**
     * Agrega un héroe a la lista interna y lo muestra en el registro.
     *
     * @param heroe el héroe a agregar.
     */
    private void agregarHeroe(SuperHeroe heroe) {
        listaHeroes.add(heroe);
        modeloRegistro.addElement(
                "[HÉROE] " + heroe.getNombre()
                        + " - " + heroe.getHabilidades());
    }

    /**
     * Crea un héroe personalizado a partir de los datos del formulario.
     */
    private void crearHeroe() {
        String nombre = txtNombreHeroe.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar un nombre para el héroe.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Set<TipoHabilidad> habilidades =
                obtenerHabilidadesHeroeSeleccionadas();
        if (habilidades.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar al menos una habilidad.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SuperHeroe nuevoHeroe = new CustomHero(nombre, habilidades);
        agregarHeroe(nuevoHeroe);

        // Limpiar formulario
        txtNombreHeroe.setText("");
        chkAcuatico.setSelected(false);
        chkVolador.setSelected(false);
        chkTrepamuros.setSelected(false);
        chkSigiloso.setSelected(false);
        chkControlFuego.setSelected(false);

        JOptionPane.showMessageDialog(this,
                "Héroe '" + nombre + "' creado exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Obtiene las habilidades seleccionadas en los checkboxes del héroe.
     *
     * @return conjunto de habilidades seleccionadas.
     */
    private Set<TipoHabilidad> obtenerHabilidadesHeroeSeleccionadas() {
        Set<TipoHabilidad> habilidades = new HashSet<>();
        if (chkAcuatico.isSelected())
            habilidades.add(TipoHabilidad.ACUATICO);
        if (chkVolador.isSelected())
            habilidades.add(TipoHabilidad.VOLADOR);
        if (chkTrepamuros.isSelected())
            habilidades.add(TipoHabilidad.TREPAMUROS);
        if (chkSigiloso.isSelected())
            habilidades.add(TipoHabilidad.SIGILOSO);
        if (chkControlFuego.isSelected())
            habilidades.add(TipoHabilidad.CONTROL_FUEGO);
        return habilidades;
    }

    /**
     * Crea una misión a partir de los datos del formulario.
     */
    private void crearMision() {
        String nombre = txtNombreMision.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar un nombre para la misión.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Set<TipoHabilidad> habilidades =
                obtenerHabilidadesMisionSeleccionadas();
        if (habilidades.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar al menos una habilidad requerida.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Mision nuevaMision = new Mision(nombre, habilidades);
        listaMisiones.add(nuevaMision);
        modeloRegistro.addElement(
                "[MISIÓN] " + nombre
                        + " - Requiere: " + habilidades);

        // Limpiar formulario
        txtNombreMision.setText("");
        chkMisionAcuatico.setSelected(false);
        chkMisionVolador.setSelected(false);
        chkMisionTrepamuros.setSelected(false);
        chkMisionSigiloso.setSelected(false);
        chkMisionControlFuego.setSelected(false);

        JOptionPane.showMessageDialog(this,
                "Misión '" + nombre + "' creada exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Obtiene las habilidades seleccionadas en los checkboxes de misión.
     *
     * @return conjunto de habilidades seleccionadas.
     */
    private Set<TipoHabilidad> obtenerHabilidadesMisionSeleccionadas() {
        Set<TipoHabilidad> habilidades = new HashSet<>();
        if (chkMisionAcuatico.isSelected())
            habilidades.add(TipoHabilidad.ACUATICO);
        if (chkMisionVolador.isSelected())
            habilidades.add(TipoHabilidad.VOLADOR);
        if (chkMisionTrepamuros.isSelected())
            habilidades.add(TipoHabilidad.TREPAMUROS);
        if (chkMisionSigiloso.isSelected())
            habilidades.add(TipoHabilidad.SIGILOSO);
        if (chkMisionControlFuego.isSelected())
            habilidades.add(TipoHabilidad.CONTROL_FUEGO);
        return habilidades;
    }

    /**
     * Ejecuta la asignación automática de todas las misiones pendientes.
     *
     * <p>Para cada misión, el sistema busca automáticamente al primer
     * héroe compatible sin intervención del usuario.</p>
     */
    private void asignarMisionesAutomaticamente() {
        if (listaMisiones.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay misiones creadas. Cree al menos una primero.",
                    "Sin misiones", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (listaHeroes.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay héroes disponibles. Cree al menos uno primero.",
                    "Sin héroes", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════\n");
        sb.append("  RESULTADOS DE ASIGNACIÓN AUTOMÁTICA\n");
        sb.append("═══════════════════════════════════════════\n\n");

        sb.append("Héroes disponibles: ")
                .append(listaHeroes.size()).append("\n");
        for (SuperHeroe h : listaHeroes) {
            sb.append("  - ").append(h.getNombre())
                    .append(" ").append(h.getHabilidades()).append("\n");
        }
        sb.append("\n");

        for (Mision mision : listaMisiones) {
            ResultadoAsignacion resultado =
                    asignador.asignarMisionAutomatica(listaHeroes, mision);

            if (resultado.isExitosa()) {
                sb.append("✓ ").append(mision.getNombre())
                        .append(" → ")
                        .append(resultado.getHeroeAsignado().getNombre())
                        .append("\n");
            } else {
                sb.append("✗ ").append(mision.getNombre())
                        .append(" → Sin héroe compatible\n");
            }
            sb.append("  ").append(resultado.getMensaje())
                    .append("\n\n");
        }

        txtResultados.setText(sb.toString());
        txtResultados.setCaretPosition(0);
    }
}