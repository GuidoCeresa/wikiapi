package it.algos.wikiapi

import grails.artefact.Artefact
import groovy.transform.CompileStatic

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

import org.apache.commons.lang.time.FastDateFormat
import org.codehaus.groovy.grails.support.encoding.CodecLookup
import org.codehaus.groovy.grails.support.encoding.Encoder
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.util.StringUtils

import java.text.SimpleDateFormat

@Artefact("TagLibrary")
class ApiTagLib {

    MessageSource messageSource


    @CompileStatic
    String messageHelper(String code, Object defaultMessage = null, List args = null, Locale locale = null) {
        if (locale == null) {
            locale = GrailsWebRequest.lookup().getLocale()
        }
        def message
        try {
            message = messageSource.getMessage(code, args == null ? null : args.toArray(), locale)
        }
        catch (NoSuchMessageException e) {
            if (defaultMessage != null) {
                if (defaultMessage instanceof Closure) {
                    message = defaultMessage()
                }
                else {
                    message = defaultMessage as String
                }
            }
        }
        return message
    }

    @CompileStatic
    static Locale resolveLocale(Object localeAttr) {
        Locale locale
        if (localeAttr instanceof Locale) {
            locale = (Locale)localeAttr
        } else if (localeAttr != null) {
            locale = StringUtils.parseLocaleString(localeAttr.toString())
        }
        if (locale == null) {
            locale = GrailsWebRequest.lookup().getLocale()
            if (locale == null) {
                locale = Locale.getDefault()
            }
        }
        return locale
    }

    /**
     * Outputs the given <code>Date</code> object in the specified format. If
     * the <code>date</code> is not given, then the current date/time is used.
     * If the <code>format</code> option is not given, then the date is output
     * using the default format.<br/>
     *
     * e.g., &lt;g:formatDate date="${myDate}" format="yyyy-MM-dd HH:mm" /&gt;<br/>
     *
     * @see java.text.SimpleDateFormat
     *
     * @emptyTag
     *
     * @attr date the date object to display; defaults to now if not specified
     * @attr format The formatting pattern to use for the date, see SimpleDateFormat
     * @attr formatName Look up format from the default MessageSource / ResourceBundle (i18n/*.properties file) with this key. If format and formatName are empty, format is looked up with 'default.date.format' key. If the key is missing, 'yyyy-MM-dd HH:mm:ss z' formatting pattern is used.
     * @attr type The type of format to use for the date / time. format or formatName aren't used when type is specified. Possible values: 'date' - shows only date part, 'time' - shows only time part, 'both'/'datetime' - shows date and time
     * @attr timeZone the time zone for formatting. See TimeZone class.
     * @attr locale Force the locale for formatting.
     * @attr style Use default date/time formatting of the country specified by the locale. Possible values: SHORT (default), MEDIUM, LONG, FULL . See DateFormat for explanation.
     * @attr dateStyle Set separate style for the date part.
     * @attr timeStyle Set separate style for the time part.
     */
    Closure formatDateWiki = { attrs ->

        def date
        if (attrs.containsKey('date')) {
            date = attrs.date
            if (date == null) return
        }
        else {
            date = new Date()
        }

        def locale = resolveLocale(attrs.locale)
        String timeStyle
        String dateStyle
        if (attrs.style != null) {
            String style = attrs.style.toString().toUpperCase()
            timeStyle = style
            dateStyle = style
        }

        if (attrs.dateStyle != null) {
            dateStyle = attrs.dateStyle.toString().toUpperCase()
        }
        if (attrs.timeStyle != null) {
            timeStyle = attrs.timeStyle.toString().toUpperCase()
        }
        String type = attrs.type?.toString()?.toUpperCase()
        def formatName = attrs.formatName
        def format = attrs.format
        def timeZone = attrs.timeZone
        if (timeZone != null) {
            if (!(timeZone instanceof TimeZone)) {
                timeZone = TimeZone.getTimeZone(timeZone as String)
            }
        }
        else {
            timeZone = TimeZone.getDefault()
        }

        def dateFormat
        if (!type) {
            if (!format && formatName) {
                format = messageHelper(formatName,null,null,locale)
                if (!format) {
                    throwTagError("Attribute [formatName] of Tag [formatDate] specifies a format key [$formatName] that does not exist within a message bundle!")
                }
            }
            else if (!format) {
                format = messageHelper('date.format', { messageHelper('default.date.format', 'yyyy-MM-dd HH:mm:ss z', null, locale) }, null, locale)
//                format='yyyy-MM-dd'
            }
            dateFormat = FastDateFormat.getInstance(format, timeZone, locale)
        }
        else {
            if (type=='DATE') {
                dateFormat = FastDateFormat.getDateInstance(parseStyle(dateStyle), timeZone, locale)
            }
            else if (type=='TIME') {
                dateFormat = FastDateFormat.getTimeInstance(parseStyle(timeStyle), timeZone, locale)
            }
            else { // 'both' or 'datetime'
                dateFormat = FastDateFormat.getDateTimeInstance(parseStyle(dateStyle), parseStyle(timeStyle), timeZone, locale)
            }
        }

//        SimpleDateFormat format2=  new SimpleDateFormat('dd-MMM-yy')
//        String risposta=format2.format(date)
//        return risposta
        return dateFormat.format(date)
    }


    def parseStyle(styleStr) {
        switch (styleStr) {
            case 'FULL':   return FastDateFormat.FULL
            case 'LONG':   return FastDateFormat.LONG
            case 'MEDIUM': return FastDateFormat.MEDIUM
            default:       return FastDateFormat.SHORT
        }
    }
}
