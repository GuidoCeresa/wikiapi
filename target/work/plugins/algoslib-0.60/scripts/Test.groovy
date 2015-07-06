// To execute: grails run-script scripts/Test.groovy
//--eliminare le sottostanti prime due righe nell'_install.groovy definitivo
String pluginBasedir = "/Users/gac/Documents/IdeaProjects/algoslib/"
String basedir = "/Users/gac/Documents/IdeaProjects/test/"
//--eliminare le sovrastanti prime due righe nell'_install.groovy definitivo

String source
String dest
source = "${pluginBasedir}"
dest = "${basedir}"

//--abilitare le sottostanti due righe nell'_install.groovy definitivo
//--utilizza le special variables provided by Gant
//source = source + "/"
//dest = dest + "/"
//--abilitare le sovrastanti due righe nell'_install.groovy definitivo

//--directory del plugin
String sourceDir = "${source}grails-app/it/algos/algos/"

//--directory dell'applicazione
String appDir = "grails-app/"
String domainDir = "${appDir}domain/"
String jobsDir = "${appDir}jobs/"
String viewsDir = "${appDir}views/"
String templatesDir = "src/templates/"
String artifactsDir = "${templatesDir}artifacts/"
String scaffoldingDir = "${templatesDir}scaffolding/"
String scriptsgDir = "scripts/"

//--copia tutti i files dalla della directory indicata
moveFile(source, dest, "${scriptsgDir}Test.groovy")
//moveFile(source, dest, "${domainDir}Plutoz.groovy")
//copyFile(source, dest, "${domainDir}Paperinoz.groovy")
//copyFile(source, dest, "${domainDir}Pippoz.groovy", "${domainDir}PipponeBello.groovy")


public static newFile(String srcDirPath, String dstDirPath, String fileName) {
    newFile(srcDirPath, dstDirPath, fileName, fileName)
} // fine del metodo

public static copyFile(String srcDirPath, String dstDirPath, String fileName) {
    copyFile(srcDirPath, dstDirPath, fileName, fileName)
} // fine del metodo

public static moveFile(String srcDirPath, String dstDirPath, String fileName) {
    moveFile(srcDirPath, dstDirPath, fileName, fileName)
} // fine del metodo

public static newFile(String srcDirPath, String dstDirPath, String srcFileName, String dstFileName) {
    String srcFile = srcDirPath + srcFileName
    String destFile = dstDirPath + dstFileName

    newFile(srcFile, destFile)
    deleteFile(srcFile)
    print('------------')
    print('Creato (NON sovrascritto): ' + dstFileName)
    print('------------')
} // fine del metodo

public static copyFile(String srcDirPath, String dstDirPath, String srcFileName, String dstFileName) {
    String srcFile = srcDirPath + srcFileName
    String destFile = dstDirPath + dstFileName

    copyFile(srcFile, destFile)
    deleteFile(srcFile)
    print('------------')
    print('Creato (o sovrascritto) e poi cancellato: ' + dstFileName)
    print('------------')
} // fine del metodo

public static moveFile(String srcDirPath, String dstDirPath, String srcFileName, String dstFileName) {
    String srcFile = srcDirPath + srcFileName
    String destFile = dstDirPath + dstFileName

    copyFile(srcFile, destFile)
    print('------------')
    print('Creato (o sovrascritto): ' + dstFileName)
    print('------------')
} // fine del metodo

public static newFile(String srcFile, String destFile) {
    new AntBuilder().copy(file: srcFile, tofile: destFile, overwrite: false)
} // fine del metodo

public static copyFile(String srcFile, String destFile) {
    new AntBuilder().copy(file: srcFile, tofile: destFile, overwrite: true)
} // fine del metodo

public static deleteFile(String pathFile) {
    new AntBuilder().delete(file: pathFile)
} // fine del metodo
