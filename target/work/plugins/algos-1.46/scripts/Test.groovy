// To execute: grails run-script scripts/Test.groovy
//--eliminare le sottostanti prime due righe nell'_install.groovy definitivo
String pluginBasedir = "/Users/gac/Documents/IdeaProjects/algos/"
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
String jobsDir = "${appDir}jobs/"
String viewsDir = "${appDir}views/"
String templatesDir = "src/templates/"
String artifactsDir = "${templatesDir}artifacts/"
String scaffoldingDir = "${templatesDir}scaffolding/"


print('------------')
print('Algos - installazione plugin')
print('------------')

// The GroovyScriptEngine needs to know the root directories of your code
String[] roots = ['./scripts']
def engine = new GroovyScriptEngine(roots)

// Load the class and create an instance
def algosTag = engine.loadScriptByName("AlgosTag.groovy")

// Load the class and create an instance
def algosType = engine.loadScriptByName("AlgosType.groovy")

// Load the class and create an instance
def algosBaseClass = engine.loadScriptByName("AlgosBase.groovy")
def ab = algosBaseClass.newInstance()
// inietta la property nel file
ab.sourceDir = sourceDir

//--copia tutti i files dalla della directory indicata
//--sposta file RefreshJob
//ab.moveFile(source, dest, "${jobsDir}RefreshJob")
ab.moveFile(source, dest, "${jobsDir}Pippoz.java")

//--sovrascrive file index
ab.copyFile(source, dest, "${viewsDir}index.gsp")

//--sovrascrive cartella scaffolding
ab.dirAlways(source, dest, scaffoldingDir)

print('------------')
print('Algos - fine plugin')
print('------------')
