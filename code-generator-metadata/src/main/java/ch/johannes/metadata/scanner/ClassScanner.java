package ch.johannes.metadata.scanner;

import com.google.common.base.Preconditions;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ClassInfo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassScanner {

    private final String rootPackage;

    private final List<String> regexPatterns;

    public ClassScanner(String rootPackage, List<String> wildcardPatterns) {
        Preconditions.checkNotNull(wildcardPatterns, "wildcardPatterns list must not be null");
        Preconditions.checkNotNull(rootPackage, "rootPackage must not be null");
        //from http://stackoverflow.com/questions/19107165/search-with-wildcard-in-a-collection-of-string
        this.regexPatterns = wildcardPatterns
                .stream()
                .map(s -> s.replaceAll("\\*", "\\\\w*"))
                .collect(Collectors.toList());
        this.rootPackage = rootPackage;
    }

    public Collection<Class<?>> scanForClasses() {
        final Map<String, ClassInfo> allClasses = new FastClasspathScanner(rootPackage).scan().getClassNameToClassInfo();

        final Collection<Class<?>> result = allClasses
                .values()
                .stream()
                .filter(this::matchAnyRegexPattern)
                .map(this::fetchClassFromClassInfo)
                .collect(Collectors.toList());

        return result;
    }

    private boolean matchAnyRegexPattern(ClassInfo classInfo) {
        final String className = fetchClassFromClassInfo(classInfo).getSimpleName();

        for(String regexPattern : this.regexPatterns) {
            if(className.matches(regexPattern)) {
                return true;
            }
        }
        return false;
    }

    private Class<?> fetchClassFromClassInfo(ClassInfo classInfo) {
        try {
            return Class.forName(classInfo.getClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
