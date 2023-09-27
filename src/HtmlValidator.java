import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {

    public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
        /* IMPLEMENT THIS METHOD! */
        Stack<HtmlTag> tagStack = new Stack<>();
        while (!tags.isEmpty()) {
            HtmlTag tag = tags.poll();
            if (!tag.isSelfClosing()) {
                if (tag.isOpenTag()) {
                    tagStack.push(tag);
                } else {
                    // Es una etiqueta de cierre
                    if (tagStack.isEmpty()) {
                        return null;
                    }
                    if (tagStack.peek().matches(tag)) {
                        tagStack.pop();
                    } else {
                        return tagStack;
                    }
                }
            }
        }
        // Verifica si quedan etiquetas de apertura sin cerrar
        if (!tagStack.isEmpty()) {
            return tagStack;
        }

        return new Stack<>();
    }
}
