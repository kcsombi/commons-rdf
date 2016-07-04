/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.rdf.jena;

import org.apache.commons.rdf.api.* ;
import org.apache.commons.rdf.jena.impl.JenaFactory;

/** RDFTermFactory with Jena-backed objects.
 *  See {@link JenaCommonsRDF} for other conversions of existing objects. 
 *  
 *  @see RDFTermFactory
 */
public final class RDFTermFactoryJena implements RDFTermFactory {
    
    @Override
    public BlankNode createBlankNode() {
        return JenaFactory.createBlankNode() ;
    }

    @Override
    public BlankNode createBlankNode(String name) {
        return JenaFactory.createBlankNode(name) ;
    }

    @Override
    public Graph createGraph() {
        return JenaFactory.createGraph() ;
    }

    @Override
    public IRI createIRI(String iri) {
        validateIRI(iri) ;
        return JenaFactory.createIRI(iri) ;
    }

    // Some simple validations - full IRI parsing is not cheap. 
    private static void validateIRI(String iri) {
        if ( iri.contains(" ") ) throw new IllegalArgumentException() ;
        if ( iri.contains("<") ) throw new IllegalArgumentException() ;
        if ( iri.contains(">") ) throw new IllegalArgumentException() ;
    }

    @Override
    public Literal createLiteral(String lexicalForm) {
        return JenaFactory.createLiteral(lexicalForm) ;
    }

    @Override
    public Literal createLiteral(String lexicalForm, IRI dataType) {
        return JenaFactory.createLiteralDT(lexicalForm, dataType.getIRIString()) ;
    }

    @Override
    public Literal createLiteral(String lexicalForm, String languageTag) {
        validateLang(languageTag) ;
        return JenaFactory.createLiteralLang(lexicalForm, languageTag) ;
    }

    private static void validateLang(String languageTag) {
        if ( languageTag.contains(" ") ) throw new IllegalArgumentException() ;
    }

    @Override
    public Triple createTriple(BlankNodeOrIRI subject, IRI predicate, RDFTerm object) {
        return JenaFactory.createTriple(subject, predicate, object) ;
    }

}

