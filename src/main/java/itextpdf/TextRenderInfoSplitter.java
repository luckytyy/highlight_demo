package itextpdf;

import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

public class TextRenderInfoSplitter implements TextExtractionStrategy {
	public TextRenderInfoSplitter(TextExtractionStrategy strategy) {
		this.strategy = strategy;
	}

	public void renderText(TextRenderInfo renderInfo) {
		for (TextRenderInfo info : renderInfo.getCharacterRenderInfos()) {
			strategy.renderText(info);
		}
	}

	public void beginTextBlock() {
		strategy.beginTextBlock();
	}

	public void endTextBlock() {
		strategy.endTextBlock();
	}

	public void renderImage(ImageRenderInfo renderInfo) {
		strategy.renderImage(renderInfo);
	}

	public String getResultantText() {
		return strategy.getResultantText();
	}

	final TextExtractionStrategy strategy;
}
