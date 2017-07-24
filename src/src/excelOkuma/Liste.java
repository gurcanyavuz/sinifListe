package excelOkuma;

import java.io.File;

public class Liste extends File {

    public Liste(File dosya) {
        super(dosya.getAbsolutePath());

    }

    @Override
    public String toString() {
        return getName();
    }
}
