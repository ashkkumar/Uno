public interface UnoViewHandler {

    void handleNextTurn(UnoGameEvent e);
    void handleDrawCard(UnoGameEvent e);
    void handlePlay(UnoGameEvent e);

    void handleUnoCall(UnoGameEvent e);
}
