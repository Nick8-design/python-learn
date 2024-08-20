package com.nickdieda.pythonlearn.common;

import android.content.Context;
import android.widget.Toast;

import org.eclipse.tm4e.core.registry.IThemeSource;

import java.util.Objects;

import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme;
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage;
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel;
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver;
import io.github.rosemoe.sora.widget.CodeEditor;

public class setpylan{
    public  void pyLang(Context act, CodeEditor out1){
        FileProviderRegistry.getInstance().addFileProvider(
                new AssetsFileResolver(act.getAssets())
        );

// Load the theme (replace "your-theme-name" with the actual theme name)
        ThemeRegistry themeRegistry = ThemeRegistry.getInstance();
        String themeAssetsPath = "textmate/darcula.json";
        try {
            themeRegistry.loadTheme(new ThemeModel(
                            IThemeSource.fromInputStream(
                                    Objects.requireNonNull(FileProviderRegistry.getInstance().tryGetInputStream(themeAssetsPath)),
                                    themeAssetsPath,
                                    null
                            ),
                            "darcula"
                    )
                    // If the theme is dark
                    // .isDark(true)
            );
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }

// Set the active theme
        themeRegistry.setTheme("darcula");
        GrammarRegistry.getInstance().loadGrammars("languages.json");  // Path to your languages.json
        String scopeName = "source.python";
        try {
            out1.setColorScheme(TextMateColorScheme.create(ThemeRegistry.getInstance()));// Create the TextMateLanguage instance
        } catch (Exception e) {

        }

        try {
            TextMateLanguage language = TextMateLanguage.create(
                    scopeName,
                    true
            );
            out1.setEditorLanguage(language);
        } catch (Exception e) {
            Toast.makeText(act, (CharSequence) e, Toast.LENGTH_LONG).show();
        }

    }
}
