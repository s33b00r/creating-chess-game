package observerinterfaces;

public interface IRedrawable {
    void repaint();

    Object getClickedOnPiece();

    void showPromotionUI(boolean show);
}
