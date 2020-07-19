grammar Java;

options {
	superClass = JavaANTLRParserBase;
	backtrack = true;
	memoize = true;
}

@lexer::header {
	package org.emftext.language.java.resource.java.mopp;
	
	import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime3_4_0.ANTLRStringStream;
import org.antlr.runtime3_4_0.RecognitionException;
}

@lexer::members {
	public List<RecognitionException> lexerExceptions  = new ArrayList<RecognitionException>();
	public List<Integer> lexerExceptionPositions = new ArrayList<Integer>();
	
	public void reportError(RecognitionException e) {
		lexerExceptions.add(e);
		lexerExceptionPositions.add(((ANTLRStringStream) input).index());
	}
}
@header{
	package org.emftext.language.java.resource.java.mopp;
	
	import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.antlr.runtime3_4_0.ANTLRInputStream;
import org.antlr.runtime3_4_0.BitSet;
import org.antlr.runtime3_4_0.CommonToken;
import org.antlr.runtime3_4_0.CommonTokenStream;
import org.antlr.runtime3_4_0.IntStream;
import org.antlr.runtime3_4_0.Lexer;
import org.antlr.runtime3_4_0.RecognitionException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
}

@members{
	private org.emftext.language.java.resource.java.IJavaTokenResolverFactory tokenResolverFactory = new org.emftext.language.java.resource.java.mopp.JavaTokenResolverFactory();
	
	/**
	 * the index of the last token that was handled by collectHiddenTokens()
	 */
	private int lastPosition;
	
	/**
	 * A flag that indicates whether the parser should remember all expected elements.
	 * This flag is set to true when using the parse for code completion. Otherwise it
	 * is set to false.
	 */
	private boolean rememberExpectedElements = false;
	
	private Object parseToIndexTypeObject;
	private int lastTokenIndex = 0;
	
	/**
	 * A list of expected elements the were collected while parsing the input stream.
	 * This list is only filled if <code>rememberExpectedElements</code> is set to
	 * true.
	 */
	private List<org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal> expectedElements = new ArrayList<org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal>();
	
	private int mismatchedTokenRecoveryTries = 0;
	/**
	 * A helper list to allow a lexer to pass errors to its parser
	 */
	protected List<RecognitionException> lexerExceptions = Collections.synchronizedList(new ArrayList<RecognitionException>());
	
	/**
	 * Another helper list to allow a lexer to pass positions of errors to its parser
	 */
	protected List<Integer> lexerExceptionPositions = Collections.synchronizedList(new ArrayList<Integer>());
	
	/**
	 * A stack for incomplete objects. This stack is used filled when the parser is
	 * used for code completion. Whenever the parser starts to read an object it is
	 * pushed on the stack. Once the element was parser completely it is popped from
	 * the stack.
	 */
	List<EObject> incompleteObjects = new ArrayList<EObject>();
	
	private int stopIncludingHiddenTokens;
	private int stopExcludingHiddenTokens;
	private int tokenIndexOfLastCompleteElement;
	
	private int expectedElementsIndexOfLastCompleteElement;
	
	/**
	 * The offset indicating the cursor position when the parser is used for code
	 * completion by calling parseToExpectedElements().
	 */
	private int cursorOffset;
	
	/**
	 * The offset of the first hidden token of the last expected element. This offset
	 * is used to discard expected elements, which are not needed for code completion.
	 */
	private int lastStartIncludingHidden;
	
	private org.emftext.language.java.resource.java.IJavaLocationMap locationMap;
	
	private org.emftext.language.java.resource.java.mopp.JavaSyntaxErrorMessageConverter syntaxErrorMessageConverter = new org.emftext.language.java.resource.java.mopp.JavaSyntaxErrorMessageConverter(tokenNames);
	
	@Override
	public void reportError(RecognitionException re) {
		addErrorToResource(syntaxErrorMessageConverter.translateParseError(re));
	}
	
	protected void addErrorToResource(final String errorMessage, final int column, final int line, final int startIndex, final int stopIndex) {
		postParseCommands.add(new org.emftext.language.java.resource.java.IJavaCommand<org.emftext.language.java.resource.java.IJavaTextResource>() {
			public boolean execute(org.emftext.language.java.resource.java.IJavaTextResource resource) {
				if (resource == null) {
					// the resource can be null if the parser is used for code completion
					return true;
				}
				resource.addProblem(new org.emftext.language.java.resource.java.IJavaProblem() {
					public org.emftext.language.java.resource.java.JavaEProblemSeverity getSeverity() {
						return org.emftext.language.java.resource.java.JavaEProblemSeverity.ERROR;
					}
					public org.emftext.language.java.resource.java.JavaEProblemType getType() {
						return org.emftext.language.java.resource.java.JavaEProblemType.SYNTAX_ERROR;
					}
					public String getMessage() {
						return errorMessage;
					}
					public Collection<org.emftext.language.java.resource.java.IJavaQuickFix> getQuickFixes() {
						return null;
					}
				}, column, line, startIndex, stopIndex);
				return true;
			}
		});
	}
	
	protected void addErrorToResource(org.emftext.language.java.resource.java.mopp.JavaLocalizedMessage message) {
		if (message == null) {
			return;
		}
		addErrorToResource(message.getMessage(), message.getColumn(), message.getLine(), message.getCharStart(), message.getCharEnd());
	}
	
	public void addExpectedElement(EClass eClass, int expectationStartIndex, int expectationEndIndex) {
		for (int expectationIndex = expectationStartIndex; expectationIndex <= expectationEndIndex; expectationIndex++) {
			addExpectedElement(eClass, org.emftext.language.java.resource.java.mopp.JavaExpectationConstants.EXPECTATIONS[expectationIndex]);
		}
	}
	
	public void addExpectedElement(EClass eClass, int expectationIndex) {
		addExpectedElement(eClass, org.emftext.language.java.resource.java.mopp.JavaExpectationConstants.EXPECTATIONS[expectationIndex]);
	}
	
	public void addExpectedElement(EClass eClass, int[] ids) {
		if (!this.rememberExpectedElements) {
			return;
		}
		int terminalID = ids[0];
		int followSetID = ids[1];
		org.emftext.language.java.resource.java.IJavaExpectedElement terminal = org.emftext.language.java.resource.java.grammar.JavaFollowSetProvider.TERMINALS[terminalID];
		org.emftext.language.java.resource.java.mopp.JavaContainedFeature[] containmentFeatures = new org.emftext.language.java.resource.java.mopp.JavaContainedFeature[ids.length - 2];
		for (int i = 2; i < ids.length; i++) {
			containmentFeatures[i - 2] = org.emftext.language.java.resource.java.grammar.JavaFollowSetProvider.LINKS[ids[i]];
		}
		org.emftext.language.java.resource.java.grammar.JavaContainmentTrace containmentTrace = new org.emftext.language.java.resource.java.grammar.JavaContainmentTrace(eClass, containmentFeatures);
		EObject container = getLastIncompleteElement();
		org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal expectedElement = new org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal(container, terminal, followSetID, containmentTrace);
		setPosition(expectedElement, input.index());
		int startIncludingHiddenTokens = expectedElement.getStartIncludingHiddenTokens();
		lastStartIncludingHidden = startIncludingHiddenTokens;
		this.expectedElements.add(expectedElement);
	}
	
	protected void collectHiddenTokens(EObject element) {
	}
	
	protected void copyLocalizationInfos(final EObject source, final EObject target) {
		if (disableLocationMap) {
			return;
		}
		final org.emftext.language.java.resource.java.IJavaLocationMap locationMap = this.locationMap;
		if (locationMap == null) {
			// the locationMap can be null if the parser is used for code completion
			return;
		}
		postParseCommands.add(new org.emftext.language.java.resource.java.IJavaCommand<org.emftext.language.java.resource.java.IJavaTextResource>() {
			public boolean execute(org.emftext.language.java.resource.java.IJavaTextResource resource) {
				locationMap.setCharStart(target, locationMap.getCharStart(source));
				locationMap.setCharEnd(target, locationMap.getCharEnd(source));
				locationMap.setColumn(target, locationMap.getColumn(source));
				locationMap.setLine(target, locationMap.getLine(source));
				return true;
			}
		});
	}
	
	protected void copyLocalizationInfos(final CommonToken source, final EObject target) {
		if (disableLocationMap) {
			return;
		}
		final org.emftext.language.java.resource.java.IJavaLocationMap locationMap = this.locationMap;
		if (locationMap == null) {
			// the locationMap can be null if the parser is used for code completion
			return;
		}
		postParseCommands.add(new org.emftext.language.java.resource.java.IJavaCommand<org.emftext.language.java.resource.java.IJavaTextResource>() {
			public boolean execute(org.emftext.language.java.resource.java.IJavaTextResource resource) {
				if (source == null) {
					return true;
				}
				locationMap.setCharStart(target, source.getStartIndex());
				locationMap.setCharEnd(target, source.getStopIndex());
				locationMap.setColumn(target, source.getCharPositionInLine());
				locationMap.setLine(target, source.getLine());
				return true;
			}
		});
	}
	
	/**
	 * Sets the end character index and the last line for the given object in the
	 * location map.
	 */
	protected void setLocalizationEnd(Collection<org.emftext.language.java.resource.java.IJavaCommand<org.emftext.language.java.resource.java.IJavaTextResource>> postParseCommands , final EObject object, final int endChar, final int endLine) {
		if (disableLocationMap) {
			return;
		}
		final org.emftext.language.java.resource.java.IJavaLocationMap locationMap = this.locationMap;
		if (locationMap == null) {
			// the locationMap can be null if the parser is used for code completion
			return;
		}
		postParseCommands.add(new org.emftext.language.java.resource.java.IJavaCommand<org.emftext.language.java.resource.java.IJavaTextResource>() {
			public boolean execute(org.emftext.language.java.resource.java.IJavaTextResource resource) {
				locationMap.setCharEnd(object, endChar);
				locationMap.setLine(object, endLine);
				return true;
			}
		});
	}
	
	public org.emftext.language.java.resource.java.IJavaTextParser createInstance(InputStream actualInputStream, String encoding) {
		try {
			if (encoding == null) {
				return new JavaParser(new CommonTokenStream(new JavaLexer(new ANTLRInputStream(actualInputStream))));
			} else {
				return new JavaParser(new CommonTokenStream(new JavaLexer(new ANTLRInputStream(actualInputStream, encoding))));
			}
		} catch (IOException e) {
			new org.emftext.language.java.resource.java.util.JavaRuntimeUtil().logError("Error while creating parser.", e);
			return null;
		}
	}
	
	/**
	 * This default constructor is only used to call createInstance() on it.
	 */
	public JavaParser() {
		super(null);
	}
	
	protected EObject doParse() throws RecognitionException {
		this.lastPosition = 0;
		// required because the lexer class can not be subclassed
		((JavaLexer) getTokenStream().getTokenSource()).lexerExceptions = lexerExceptions;
		((JavaLexer) getTokenStream().getTokenSource()).lexerExceptionPositions = lexerExceptionPositions;
		Object typeObject = getTypeObject();
		if (typeObject == null) {
			return start();
		} else if (typeObject instanceof EClass) {
			EClass type = (EClass) typeObject;
			if (type.getInstanceClass() == org.emftext.language.java.containers.EmptyModel.class) {
				return parse_org_emftext_language_java_containers_EmptyModel();
			}
			if (type.getInstanceClass() == org.emftext.language.java.containers.Package.class) {
				return parse_org_emftext_language_java_containers_Package();
			}
			if (type.getInstanceClass() == org.emftext.language.java.containers.CompilationUnit.class) {
				return parse_org_emftext_language_java_containers_CompilationUnit();
			}
			if (type.getInstanceClass() == org.emftext.language.java.imports.ClassifierImport.class) {
				return parse_org_emftext_language_java_imports_ClassifierImport();
			}
			if (type.getInstanceClass() == org.emftext.language.java.imports.PackageImport.class) {
				return parse_org_emftext_language_java_imports_PackageImport();
			}
			if (type.getInstanceClass() == org.emftext.language.java.imports.StaticMemberImport.class) {
				return parse_org_emftext_language_java_imports_StaticMemberImport();
			}
			if (type.getInstanceClass() == org.emftext.language.java.imports.StaticClassifierImport.class) {
				return parse_org_emftext_language_java_imports_StaticClassifierImport();
			}
			if (type.getInstanceClass() == org.emftext.language.java.classifiers.Class.class) {
				return parse_org_emftext_language_java_classifiers_Class();
			}
			if (type.getInstanceClass() == org.emftext.language.java.classifiers.AnonymousClass.class) {
				return parse_org_emftext_language_java_classifiers_AnonymousClass();
			}
			if (type.getInstanceClass() == org.emftext.language.java.classifiers.Interface.class) {
				return parse_org_emftext_language_java_classifiers_Interface();
			}
			if (type.getInstanceClass() == org.emftext.language.java.classifiers.Enumeration.class) {
				return parse_org_emftext_language_java_classifiers_Enumeration();
			}
			if (type.getInstanceClass() == org.emftext.language.java.classifiers.Annotation.class) {
				return parse_org_emftext_language_java_classifiers_Annotation();
			}
			if (type.getInstanceClass() == org.emftext.language.java.annotations.AnnotationInstance.class) {
				return parse_org_emftext_language_java_annotations_AnnotationInstance();
			}
			if (type.getInstanceClass() == org.emftext.language.java.annotations.SingleAnnotationParameter.class) {
				return parse_org_emftext_language_java_annotations_SingleAnnotationParameter();
			}
			if (type.getInstanceClass() == org.emftext.language.java.annotations.AnnotationParameterList.class) {
				return parse_org_emftext_language_java_annotations_AnnotationParameterList();
			}
			if (type.getInstanceClass() == org.emftext.language.java.annotations.AnnotationAttributeSetting.class) {
				return parse_org_emftext_language_java_annotations_AnnotationAttributeSetting();
			}
			if (type.getInstanceClass() == org.emftext.language.java.generics.TypeParameter.class) {
				return parse_org_emftext_language_java_generics_TypeParameter();
			}
			if (type.getInstanceClass() == org.emftext.language.java.members.EnumConstant.class) {
				return parse_org_emftext_language_java_members_EnumConstant();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.Block.class) {
				return parse_org_emftext_language_java_statements_Block();
			}
			if (type.getInstanceClass() == org.emftext.language.java.members.Constructor.class) {
				return parse_org_emftext_language_java_members_Constructor();
			}
			if (type.getInstanceClass() == org.emftext.language.java.members.InterfaceMethod.class) {
				return parse_org_emftext_language_java_members_InterfaceMethod();
			}
			if (type.getInstanceClass() == org.emftext.language.java.members.ClassMethod.class) {
				return parse_org_emftext_language_java_members_ClassMethod();
			}
			if (type.getInstanceClass() == org.emftext.language.java.annotations.AnnotationAttribute.class) {
				return parse_org_emftext_language_java_annotations_AnnotationAttribute();
			}
			if (type.getInstanceClass() == org.emftext.language.java.parameters.OrdinaryParameter.class) {
				return parse_org_emftext_language_java_parameters_OrdinaryParameter();
			}
			if (type.getInstanceClass() == org.emftext.language.java.parameters.VariableLengthParameter.class) {
				return parse_org_emftext_language_java_parameters_VariableLengthParameter();
			}
			if (type.getInstanceClass() == org.emftext.language.java.variables.LocalVariable.class) {
				return parse_org_emftext_language_java_variables_LocalVariable();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.LocalVariableStatement.class) {
				return parse_org_emftext_language_java_statements_LocalVariableStatement();
			}
			if (type.getInstanceClass() == org.emftext.language.java.variables.AdditionalLocalVariable.class) {
				return parse_org_emftext_language_java_variables_AdditionalLocalVariable();
			}
			if (type.getInstanceClass() == org.emftext.language.java.members.Field.class) {
				return parse_org_emftext_language_java_members_Field();
			}
			if (type.getInstanceClass() == org.emftext.language.java.members.AdditionalField.class) {
				return parse_org_emftext_language_java_members_AdditionalField();
			}
			if (type.getInstanceClass() == org.emftext.language.java.members.EmptyMember.class) {
				return parse_org_emftext_language_java_members_EmptyMember();
			}
			if (type.getInstanceClass() == org.emftext.language.java.instantiations.NewConstructorCall.class) {
				return parse_org_emftext_language_java_instantiations_NewConstructorCall();
			}
			if (type.getInstanceClass() == org.emftext.language.java.instantiations.ExplicitConstructorCall.class) {
				return parse_org_emftext_language_java_instantiations_ExplicitConstructorCall();
			}
			if (type.getInstanceClass() == org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped.class) {
				return parse_org_emftext_language_java_arrays_ArrayInstantiationByValuesTyped();
			}
			if (type.getInstanceClass() == org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped.class) {
				return parse_org_emftext_language_java_arrays_ArrayInstantiationByValuesUntyped();
			}
			if (type.getInstanceClass() == org.emftext.language.java.arrays.ArrayInstantiationBySize.class) {
				return parse_org_emftext_language_java_arrays_ArrayInstantiationBySize();
			}
			if (type.getInstanceClass() == org.emftext.language.java.arrays.ArrayInitializer.class) {
				return parse_org_emftext_language_java_arrays_ArrayInitializer();
			}
			if (type.getInstanceClass() == org.emftext.language.java.arrays.ArraySelector.class) {
				return parse_org_emftext_language_java_arrays_ArraySelector();
			}
			if (type.getInstanceClass() == org.emftext.language.java.types.NamespaceClassifierReference.class) {
				return parse_org_emftext_language_java_types_NamespaceClassifierReference();
			}
			if (type.getInstanceClass() == org.emftext.language.java.types.ClassifierReference.class) {
				return parse_org_emftext_language_java_types_ClassifierReference();
			}
			if (type.getInstanceClass() == org.emftext.language.java.references.MethodCall.class) {
				return parse_org_emftext_language_java_references_MethodCall();
			}
			if (type.getInstanceClass() == org.emftext.language.java.references.IdentifierReference.class) {
				return parse_org_emftext_language_java_references_IdentifierReference();
			}
			if (type.getInstanceClass() == org.emftext.language.java.references.ReflectiveClassReference.class) {
				return parse_org_emftext_language_java_references_ReflectiveClassReference();
			}
			if (type.getInstanceClass() == org.emftext.language.java.references.SelfReference.class) {
				return parse_org_emftext_language_java_references_SelfReference();
			}
			if (type.getInstanceClass() == org.emftext.language.java.references.PrimitiveTypeReference.class) {
				return parse_org_emftext_language_java_references_PrimitiveTypeReference();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.This.class) {
				return parse_org_emftext_language_java_literals_This();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.Super.class) {
				return parse_org_emftext_language_java_literals_Super();
			}
			if (type.getInstanceClass() == org.emftext.language.java.references.StringReference.class) {
				return parse_org_emftext_language_java_references_StringReference();
			}
			if (type.getInstanceClass() == org.emftext.language.java.generics.QualifiedTypeArgument.class) {
				return parse_org_emftext_language_java_generics_QualifiedTypeArgument();
			}
			if (type.getInstanceClass() == org.emftext.language.java.generics.UnknownTypeArgument.class) {
				return parse_org_emftext_language_java_generics_UnknownTypeArgument();
			}
			if (type.getInstanceClass() == org.emftext.language.java.generics.ExtendsTypeArgument.class) {
				return parse_org_emftext_language_java_generics_ExtendsTypeArgument();
			}
			if (type.getInstanceClass() == org.emftext.language.java.generics.SuperTypeArgument.class) {
				return parse_org_emftext_language_java_generics_SuperTypeArgument();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.Assert.class) {
				return parse_org_emftext_language_java_statements_Assert();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.Condition.class) {
				return parse_org_emftext_language_java_statements_Condition();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.ForLoop.class) {
				return parse_org_emftext_language_java_statements_ForLoop();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.ForEachLoop.class) {
				return parse_org_emftext_language_java_statements_ForEachLoop();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.WhileLoop.class) {
				return parse_org_emftext_language_java_statements_WhileLoop();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.DoWhileLoop.class) {
				return parse_org_emftext_language_java_statements_DoWhileLoop();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.EmptyStatement.class) {
				return parse_org_emftext_language_java_statements_EmptyStatement();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.SynchronizedBlock.class) {
				return parse_org_emftext_language_java_statements_SynchronizedBlock();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.TryBlock.class) {
				return parse_org_emftext_language_java_statements_TryBlock();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.CatchBlock.class) {
				return parse_org_emftext_language_java_statements_CatchBlock();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.Switch.class) {
				return parse_org_emftext_language_java_statements_Switch();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.NormalSwitchCase.class) {
				return parse_org_emftext_language_java_statements_NormalSwitchCase();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.DefaultSwitchCase.class) {
				return parse_org_emftext_language_java_statements_DefaultSwitchCase();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.Return.class) {
				return parse_org_emftext_language_java_statements_Return();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.Throw.class) {
				return parse_org_emftext_language_java_statements_Throw();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.Break.class) {
				return parse_org_emftext_language_java_statements_Break();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.Continue.class) {
				return parse_org_emftext_language_java_statements_Continue();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.JumpLabel.class) {
				return parse_org_emftext_language_java_statements_JumpLabel();
			}
			if (type.getInstanceClass() == org.emftext.language.java.statements.ExpressionStatement.class) {
				return parse_org_emftext_language_java_statements_ExpressionStatement();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.ExpressionList.class) {
				return parse_org_emftext_language_java_expressions_ExpressionList();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.AssignmentExpression.class) {
				return parse_org_emftext_language_java_expressions_AssignmentExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.ConditionalExpression.class) {
				return parse_org_emftext_language_java_expressions_ConditionalExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.ConditionalOrExpression.class) {
				return parse_org_emftext_language_java_expressions_ConditionalOrExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.ConditionalAndExpression.class) {
				return parse_org_emftext_language_java_expressions_ConditionalAndExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.InclusiveOrExpression.class) {
				return parse_org_emftext_language_java_expressions_InclusiveOrExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.ExclusiveOrExpression.class) {
				return parse_org_emftext_language_java_expressions_ExclusiveOrExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.AndExpression.class) {
				return parse_org_emftext_language_java_expressions_AndExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.EqualityExpression.class) {
				return parse_org_emftext_language_java_expressions_EqualityExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.InstanceOfExpression.class) {
				return parse_org_emftext_language_java_expressions_InstanceOfExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.RelationExpression.class) {
				return parse_org_emftext_language_java_expressions_RelationExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.ShiftExpression.class) {
				return parse_org_emftext_language_java_expressions_ShiftExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.AdditiveExpression.class) {
				return parse_org_emftext_language_java_expressions_AdditiveExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.MultiplicativeExpression.class) {
				return parse_org_emftext_language_java_expressions_MultiplicativeExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.UnaryExpression.class) {
				return parse_org_emftext_language_java_expressions_UnaryExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.SuffixUnaryModificationExpression.class) {
				return parse_org_emftext_language_java_expressions_SuffixUnaryModificationExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.PrefixUnaryModificationExpression.class) {
				return parse_org_emftext_language_java_expressions_PrefixUnaryModificationExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.CastExpression.class) {
				return parse_org_emftext_language_java_expressions_CastExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.expressions.NestedExpression.class) {
				return parse_org_emftext_language_java_expressions_NestedExpression();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.Assignment.class) {
				return parse_org_emftext_language_java_operators_Assignment();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.AssignmentPlus.class) {
				return parse_org_emftext_language_java_operators_AssignmentPlus();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.AssignmentMinus.class) {
				return parse_org_emftext_language_java_operators_AssignmentMinus();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.AssignmentMultiplication.class) {
				return parse_org_emftext_language_java_operators_AssignmentMultiplication();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.AssignmentDivision.class) {
				return parse_org_emftext_language_java_operators_AssignmentDivision();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.AssignmentAnd.class) {
				return parse_org_emftext_language_java_operators_AssignmentAnd();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.AssignmentOr.class) {
				return parse_org_emftext_language_java_operators_AssignmentOr();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.AssignmentExclusiveOr.class) {
				return parse_org_emftext_language_java_operators_AssignmentExclusiveOr();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.AssignmentModulo.class) {
				return parse_org_emftext_language_java_operators_AssignmentModulo();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.AssignmentLeftShift.class) {
				return parse_org_emftext_language_java_operators_AssignmentLeftShift();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.AssignmentRightShift.class) {
				return parse_org_emftext_language_java_operators_AssignmentRightShift();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.AssignmentUnsignedRightShift.class) {
				return parse_org_emftext_language_java_operators_AssignmentUnsignedRightShift();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.Addition.class) {
				return parse_org_emftext_language_java_operators_Addition();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.Subtraction.class) {
				return parse_org_emftext_language_java_operators_Subtraction();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.Multiplication.class) {
				return parse_org_emftext_language_java_operators_Multiplication();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.Division.class) {
				return parse_org_emftext_language_java_operators_Division();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.Remainder.class) {
				return parse_org_emftext_language_java_operators_Remainder();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.LessThan.class) {
				return parse_org_emftext_language_java_operators_LessThan();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.LessThanOrEqual.class) {
				return parse_org_emftext_language_java_operators_LessThanOrEqual();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.GreaterThan.class) {
				return parse_org_emftext_language_java_operators_GreaterThan();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.GreaterThanOrEqual.class) {
				return parse_org_emftext_language_java_operators_GreaterThanOrEqual();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.LeftShift.class) {
				return parse_org_emftext_language_java_operators_LeftShift();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.RightShift.class) {
				return parse_org_emftext_language_java_operators_RightShift();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.UnsignedRightShift.class) {
				return parse_org_emftext_language_java_operators_UnsignedRightShift();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.Equal.class) {
				return parse_org_emftext_language_java_operators_Equal();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.NotEqual.class) {
				return parse_org_emftext_language_java_operators_NotEqual();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.PlusPlus.class) {
				return parse_org_emftext_language_java_operators_PlusPlus();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.MinusMinus.class) {
				return parse_org_emftext_language_java_operators_MinusMinus();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.Complement.class) {
				return parse_org_emftext_language_java_operators_Complement();
			}
			if (type.getInstanceClass() == org.emftext.language.java.operators.Negate.class) {
				return parse_org_emftext_language_java_operators_Negate();
			}
			if (type.getInstanceClass() == org.emftext.language.java.arrays.ArrayDimension.class) {
				return parse_org_emftext_language_java_arrays_ArrayDimension();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.NullLiteral.class) {
				return parse_org_emftext_language_java_literals_NullLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.modifiers.Public.class) {
				return parse_org_emftext_language_java_modifiers_Public();
			}
			if (type.getInstanceClass() == org.emftext.language.java.modifiers.Abstract.class) {
				return parse_org_emftext_language_java_modifiers_Abstract();
			}
			if (type.getInstanceClass() == org.emftext.language.java.modifiers.Protected.class) {
				return parse_org_emftext_language_java_modifiers_Protected();
			}
			if (type.getInstanceClass() == org.emftext.language.java.modifiers.Private.class) {
				return parse_org_emftext_language_java_modifiers_Private();
			}
			if (type.getInstanceClass() == org.emftext.language.java.modifiers.Final.class) {
				return parse_org_emftext_language_java_modifiers_Final();
			}
			if (type.getInstanceClass() == org.emftext.language.java.modifiers.Static.class) {
				return parse_org_emftext_language_java_modifiers_Static();
			}
			if (type.getInstanceClass() == org.emftext.language.java.modifiers.Native.class) {
				return parse_org_emftext_language_java_modifiers_Native();
			}
			if (type.getInstanceClass() == org.emftext.language.java.modifiers.Synchronized.class) {
				return parse_org_emftext_language_java_modifiers_Synchronized();
			}
			if (type.getInstanceClass() == org.emftext.language.java.modifiers.Transient.class) {
				return parse_org_emftext_language_java_modifiers_Transient();
			}
			if (type.getInstanceClass() == org.emftext.language.java.modifiers.Volatile.class) {
				return parse_org_emftext_language_java_modifiers_Volatile();
			}
			if (type.getInstanceClass() == org.emftext.language.java.modifiers.Strictfp.class) {
				return parse_org_emftext_language_java_modifiers_Strictfp();
			}
			if (type.getInstanceClass() == org.emftext.language.java.types.Void.class) {
				return parse_org_emftext_language_java_types_Void();
			}
			if (type.getInstanceClass() == org.emftext.language.java.types.Boolean.class) {
				return parse_org_emftext_language_java_types_Boolean();
			}
			if (type.getInstanceClass() == org.emftext.language.java.types.Char.class) {
				return parse_org_emftext_language_java_types_Char();
			}
			if (type.getInstanceClass() == org.emftext.language.java.types.Byte.class) {
				return parse_org_emftext_language_java_types_Byte();
			}
			if (type.getInstanceClass() == org.emftext.language.java.types.Short.class) {
				return parse_org_emftext_language_java_types_Short();
			}
			if (type.getInstanceClass() == org.emftext.language.java.types.Int.class) {
				return parse_org_emftext_language_java_types_Int();
			}
			if (type.getInstanceClass() == org.emftext.language.java.types.Long.class) {
				return parse_org_emftext_language_java_types_Long();
			}
			if (type.getInstanceClass() == org.emftext.language.java.types.Float.class) {
				return parse_org_emftext_language_java_types_Float();
			}
			if (type.getInstanceClass() == org.emftext.language.java.types.Double.class) {
				return parse_org_emftext_language_java_types_Double();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.DecimalLongLiteral.class) {
				return parse_org_emftext_language_java_literals_DecimalLongLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.DecimalFloatLiteral.class) {
				return parse_org_emftext_language_java_literals_DecimalFloatLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.DecimalIntegerLiteral.class) {
				return parse_org_emftext_language_java_literals_DecimalIntegerLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.DecimalDoubleLiteral.class) {
				return parse_org_emftext_language_java_literals_DecimalDoubleLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.HexLongLiteral.class) {
				return parse_org_emftext_language_java_literals_HexLongLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.HexFloatLiteral.class) {
				return parse_org_emftext_language_java_literals_HexFloatLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.HexDoubleLiteral.class) {
				return parse_org_emftext_language_java_literals_HexDoubleLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.HexIntegerLiteral.class) {
				return parse_org_emftext_language_java_literals_HexIntegerLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.OctalLongLiteral.class) {
				return parse_org_emftext_language_java_literals_OctalLongLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.OctalIntegerLiteral.class) {
				return parse_org_emftext_language_java_literals_OctalIntegerLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.CharacterLiteral.class) {
				return parse_org_emftext_language_java_literals_CharacterLiteral();
			}
			if (type.getInstanceClass() == org.emftext.language.java.literals.BooleanLiteral.class) {
				return parse_org_emftext_language_java_literals_BooleanLiteral();
			}
		}
		throw new org.emftext.language.java.resource.java.mopp.JavaUnexpectedContentTypeException(typeObject);
	}
	
	public int getMismatchedTokenRecoveryTries() {
		return mismatchedTokenRecoveryTries;
	}
	
	public Object getMissingSymbol(IntStream arg0, RecognitionException arg1, int arg2, BitSet arg3) {
		mismatchedTokenRecoveryTries++;
		return super.getMissingSymbol(arg0, arg1, arg2, arg3);
	}
	
	public Object getParseToIndexTypeObject() {
		return parseToIndexTypeObject;
	}
	
	protected Object getTypeObject() {
		Object typeObject = getParseToIndexTypeObject();
		if (typeObject != null) {
			return typeObject;
		}
		Map<?,?> options = getOptions();
		if (options != null) {
			typeObject = options.get(org.emftext.language.java.resource.java.IJavaOptions.RESOURCE_CONTENT_TYPE);
		}
		return typeObject;
	}
	
	/**
	 * Implementation that calls {@link #doParse()} and handles the thrown
	 * RecognitionExceptions.
	 */
	public org.emftext.language.java.resource.java.IJavaParseResult parse() {
		// Reset parser state
		terminateParsing = false;
		postParseCommands = new ArrayList<org.emftext.language.java.resource.java.IJavaCommand<org.emftext.language.java.resource.java.IJavaTextResource>>();
		org.emftext.language.java.resource.java.mopp.JavaParseResult parseResult = new org.emftext.language.java.resource.java.mopp.JavaParseResult();
		if (disableLocationMap) {
			locationMap = new org.emftext.language.java.resource.java.mopp.JavaDevNullLocationMap();
		} else {
			locationMap = new org.emftext.language.java.resource.java.mopp.JavaLocationMap();
		}
		// Run parser
		try {
			EObject result =  doParse();
			if (lexerExceptions.isEmpty()) {
				parseResult.setRoot(result);
				parseResult.setLocationMap(locationMap);
			}
		} catch (RecognitionException re) {
			addErrorToResource(syntaxErrorMessageConverter.translateParseError(re));
		} catch (IllegalArgumentException iae) {
			if ("The 'no null' constraint is violated".equals(iae.getMessage())) {
				// can be caused if a null is set on EMF models where not allowed. this will just
				// happen if other errors occurred before
			} else {
				iae.printStackTrace();
			}
		}
		for (RecognitionException re : lexerExceptions) {
			addErrorToResource(syntaxErrorMessageConverter.translateLexicalError(re, lexerExceptions, lexerExceptionPositions));
		}
		parseResult.getPostParseCommands().addAll(postParseCommands);
		return parseResult;
	}
	
	public List<org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal> parseToExpectedElements(EClass type, org.emftext.language.java.resource.java.IJavaTextResource dummyResource, int cursorOffset) {
		this.rememberExpectedElements = true;
		this.parseToIndexTypeObject = type;
		this.cursorOffset = cursorOffset;
		this.lastStartIncludingHidden = -1;
		final CommonTokenStream tokenStream = (CommonTokenStream) getTokenStream();
		org.emftext.language.java.resource.java.IJavaParseResult result = parse();
		for (EObject incompleteObject : incompleteObjects) {
			Lexer lexer = (Lexer) tokenStream.getTokenSource();
			int endChar = lexer.getCharIndex();
			int endLine = lexer.getLine();
			setLocalizationEnd(result.getPostParseCommands(), incompleteObject, endChar, endLine);
		}
		if (result != null) {
			EObject root = result.getRoot();
			if (root != null) {
				dummyResource.getContentsInternal().add(root);
			}
			for (org.emftext.language.java.resource.java.IJavaCommand<org.emftext.language.java.resource.java.IJavaTextResource> command : result.getPostParseCommands()) {
				command.execute(dummyResource);
			}
		}
		int lastFollowSetID = expectedElements.get(expectedElementsIndexOfLastCompleteElement).getFollowSetID();
		Set<org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal> currentFollowSet = new LinkedHashSet<org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal>();
		List<org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal> newFollowSet = new ArrayList<org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal>();
		for (int i = expectedElementsIndexOfLastCompleteElement; i >= 0; i--) {
			org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal expectedElementI = expectedElements.get(i);
			if (expectedElementI.getFollowSetID() == lastFollowSetID) {
				currentFollowSet.add(expectedElementI);
			} else {
				break;
			}
		}
		int followSetID = 804;
		int i;
		for (i = tokenIndexOfLastCompleteElement; i < tokenStream.size(); i++) {
			CommonToken nextToken = (CommonToken) tokenStream.get(i);
			if (nextToken.getType() < 0) {
				break;
			}
			if (nextToken.getChannel() == 99) {
				// hidden tokens do not reduce the follow set
			} else {
				// now that we have found the next visible token the position for that expected
				// terminals can be set
				for (org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal nextFollow : newFollowSet) {
					lastTokenIndex = 0;
					setPosition(nextFollow, i);
				}
				newFollowSet.clear();
				// normal tokens do reduce the follow set - only elements that match the token are
				// kept
				for (org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal nextFollow : currentFollowSet) {
					if (nextFollow.getTerminal().getTokenNames().contains(getTokenNames()[nextToken.getType()])) {
						// keep this one - it matches
						Collection<org.emftext.language.java.resource.java.util.JavaPair<org.emftext.language.java.resource.java.IJavaExpectedElement, org.emftext.language.java.resource.java.mopp.JavaContainedFeature[]>> newFollowers = nextFollow.getTerminal().getFollowers();
						for (org.emftext.language.java.resource.java.util.JavaPair<org.emftext.language.java.resource.java.IJavaExpectedElement, org.emftext.language.java.resource.java.mopp.JavaContainedFeature[]> newFollowerPair : newFollowers) {
							org.emftext.language.java.resource.java.IJavaExpectedElement newFollower = newFollowerPair.getLeft();
							EObject container = getLastIncompleteElement();
							org.emftext.language.java.resource.java.grammar.JavaContainmentTrace containmentTrace = new org.emftext.language.java.resource.java.grammar.JavaContainmentTrace(null, newFollowerPair.getRight());
							org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal newFollowTerminal = new org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal(container, newFollower, followSetID, containmentTrace);
							newFollowSet.add(newFollowTerminal);
							expectedElements.add(newFollowTerminal);
						}
					}
				}
				currentFollowSet.clear();
				currentFollowSet.addAll(newFollowSet);
			}
			followSetID++;
		}
		// after the last token in the stream we must set the position for the elements
		// that were added during the last iteration of the loop
		for (org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal nextFollow : newFollowSet) {
			lastTokenIndex = 0;
			setPosition(nextFollow, i);
		}
		return this.expectedElements;
	}
	
	public void setPosition(org.emftext.language.java.resource.java.mopp.JavaExpectedTerminal expectedElement, int tokenIndex) {
		int currentIndex = Math.max(0, tokenIndex);
		for (int index = lastTokenIndex; index < currentIndex; index++) {
			if (index >= input.size()) {
				break;
			}
			CommonToken tokenAtIndex = (CommonToken) input.get(index);
			stopIncludingHiddenTokens = tokenAtIndex.getStopIndex() + 1;
			if (tokenAtIndex.getChannel() != 99 && !anonymousTokens.contains(tokenAtIndex)) {
				stopExcludingHiddenTokens = tokenAtIndex.getStopIndex() + 1;
			}
		}
		lastTokenIndex = Math.max(0, currentIndex);
		expectedElement.setPosition(stopExcludingHiddenTokens, stopIncludingHiddenTokens);
	}
	
	public Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
		if (!rememberExpectedElements) {
			return super.recoverFromMismatchedToken(input, ttype, follow);
		} else {
			return null;
		}
	}
	
	private void startIncompleteElement(Object object) {
		if (object instanceof EObject) {
			this.incompleteObjects.add((EObject) object);
		}
	}
	
	private void completedElement(Object object, boolean isContainment) {
		if (isContainment && !this.incompleteObjects.isEmpty()) {
			this.incompleteObjects.remove(object);
		}
		if (object instanceof EObject) {
			this.tokenIndexOfLastCompleteElement = getTokenStream().index();
			this.expectedElementsIndexOfLastCompleteElement = expectedElements.size() - 1;
		}
	}
	
	private EObject getLastIncompleteElement() {
		if (incompleteObjects.isEmpty()) {
			return null;
		}
		return incompleteObjects.get(incompleteObjects.size() - 1);
	}
	
}

start returns [ EObject element = null]
:
	{
		// follow set for start rule(s)
		addExpectedElement(null, 0);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 1, 4);
		addExpectedElement(null, 5);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 6, 57);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 58);
		addExpectedElement(null, 59);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getEmptyModel(), 60, 63);
		addExpectedElement(null, 64);
		expectedElementsIndexOfLastCompleteElement = 64;
	}
	(
		c0 = parse_org_emftext_language_java_containers_CompilationUnit{ element = c0; }
		|  		c1 = parse_org_emftext_language_java_containers_Package{ element = c1; }
		|  		c2 = parse_org_emftext_language_java_containers_EmptyModel{ element = c2; }
	)
	EOF	{
		retrieveLayoutInformation(element, null, null, false);
	}
	
;

parse_org_emftext_language_java_containers_EmptyModel returns [org.emftext.language.java.containers.EmptyModel element = null]
@init{
}
:
	(
		(
			(
				a0_0 = parse_org_emftext_language_java_imports_Import				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createEmptyModel();
						startIncompleteElement(element);
					}
					if (a0_0 != null) {
						if (a0_0 != null) {
							Object value = a0_0;
							addObjectToList(element, org.emftext.language.java.containers.ContainersPackage.EMPTY_MODEL__IMPORTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_0_0_0_0_0_0_0, a0_0, true);
						copyLocalizationInfos(a0_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 65);
			}
			
			a1 = ';' {
				if (element == null) {
					element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createEmptyModel();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_0_0_0_0_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getEmptyModel(), 66, 69);
				addExpectedElement(null, 70);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getEmptyModel(), 71, 74);
		addExpectedElement(null, 75);
	}
	
	(
		(
			a2 = ';' {
				if (element == null) {
					element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createEmptyModel();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_0_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 76);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 77);
	}
	
;

parse_org_emftext_language_java_containers_Package returns [org.emftext.language.java.containers.Package element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_annotations_AnnotationInstance			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createPackage();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.containers.ContainersPackage.PACKAGE__ANNOTATIONS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_1_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 78);
		addExpectedElement(null, 79);
	}
	
	a1 = 'package' {
		if (element == null) {
			element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createPackage();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_1_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 80, 81);
	}
	
	(
		(
			(
				a2 = IDENTIFIER				
				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createPackage();
						startIncompleteElement(element);
					}
					if (a2 != null) {
						org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
						tokenResolver.setOptions(getOptions());
						org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAMESPACES), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
						}
						java.lang.String resolved = (java.lang.String) resolvedObject;
						if (resolved != null) {
							Object value = resolved;
							addObjectToList(element, org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAMESPACES, value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_1_0_0_2_0_0_0, resolved, true);
						copyLocalizationInfos((CommonToken) a2, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 82);
			}
			
			a3 = '.' {
				if (element == null) {
					element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createPackage();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_1_0_0_2_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 83, 84);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, 85, 86);
	}
	
	(
		a4 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createPackage();
				startIncompleteElement(element);
			}
			if (a4 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a4).getLine(), ((CommonToken) a4).getCharPositionInLine(), ((CommonToken) a4).getStartIndex(), ((CommonToken) a4).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_1_0_0_3, resolved, true);
				copyLocalizationInfos((CommonToken) a4, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 87);
	}
	
	a5 = ';' {
		if (element == null) {
			element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createPackage();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_1_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 88);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 89, 92);
		addExpectedElement(null, 93);
	}
	
	(
		(
			a6 = ';' {
				if (element == null) {
					element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createPackage();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_1_0_0_5_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a6, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 94, 97);
				addExpectedElement(null, 98);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 99, 102);
		addExpectedElement(null, 103);
	}
	
	(
		(
			(
				a7_0 = parse_org_emftext_language_java_imports_Import				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createPackage();
						startIncompleteElement(element);
					}
					if (a7_0 != null) {
						if (a7_0 != null) {
							Object value = a7_0;
							addObjectToList(element, org.emftext.language.java.containers.ContainersPackage.PACKAGE__IMPORTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_1_0_0_8_0_0_0, a7_0, true);
						copyLocalizationInfos(a7_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 104, 107);
				addExpectedElement(null, 108);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 109, 112);
		addExpectedElement(null, 113);
	}
	
	(
		(
			a8 = ';' {
				if (element == null) {
					element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createPackage();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_1_0_0_9_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a8, element);
			}
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 114);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 115);
	}
	
;

parse_org_emftext_language_java_containers_CompilationUnit returns [org.emftext.language.java.containers.CompilationUnit element = null]
@init{
}
:
	(
		(
			a0 = 'package' {
				if (element == null) {
					element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createCompilationUnit();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_2_0_0_0_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a0, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 116);
			}
			
			(
				a1 = IDENTIFIER				
				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createCompilationUnit();
						startIncompleteElement(element);
					}
					if (a1 != null) {
						org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
						tokenResolver.setOptions(getOptions());
						org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
						}
						java.lang.String resolved = (java.lang.String) resolvedObject;
						if (resolved != null) {
							Object value = resolved;
							addObjectToList(element, org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES, value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_2_0_0_0_0_0_1, resolved, true);
						copyLocalizationInfos((CommonToken) a1, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 117, 118);
			}
			
			(
				(
					a2 = '.' {
						if (element == null) {
							element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createCompilationUnit();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_2_0_0_0_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a2, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(null, 119);
					}
					
					(
						a3 = IDENTIFIER						
						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createCompilationUnit();
								startIncompleteElement(element);
							}
							if (a3 != null) {
								org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
								tokenResolver.setOptions(getOptions());
								org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
								tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES), result);
								Object resolvedObject = result.getResolvedToken();
								if (resolvedObject == null) {
									addErrorToResource(result.getErrorMessage(), ((CommonToken) a3).getLine(), ((CommonToken) a3).getCharPositionInLine(), ((CommonToken) a3).getStartIndex(), ((CommonToken) a3).getStopIndex());
								}
								java.lang.String resolved = (java.lang.String) resolvedObject;
								if (resolved != null) {
									Object value = resolved;
									addObjectToList(element, org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES, value);
									completedElement(value, false);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_2_0_0_0_0_0_2_0_0_1, resolved, true);
								copyLocalizationInfos((CommonToken) a3, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 120, 121);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 122, 123);
			}
			
			a4 = ';' {
				if (element == null) {
					element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createCompilationUnit();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_2_0_0_0_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a4, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 124, 127);
				addExpectedElement(null, 128);
				addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 129, 180);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 181, 184);
		addExpectedElement(null, 185);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 186, 237);
	}
	
	(
		(
			(
				a5_0 = parse_org_emftext_language_java_imports_Import				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createCompilationUnit();
						startIncompleteElement(element);
					}
					if (a5_0 != null) {
						if (a5_0 != null) {
							Object value = a5_0;
							addObjectToList(element, org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__IMPORTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_2_0_0_3_0_0_0, a5_0, true);
						copyLocalizationInfos(a5_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 238, 241);
				addExpectedElement(null, 242);
				addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 243, 294);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 295, 298);
		addExpectedElement(null, 299);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 300, 351);
	}
	
	(
		(
			a6 = ';' {
				if (element == null) {
					element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createCompilationUnit();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_2_0_0_4_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a6, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 352);
				addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 353, 404);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, 405);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 406, 457);
	}
	
	(
		(
			(
				a7_0 = parse_org_emftext_language_java_classifiers_ConcreteClassifier				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createCompilationUnit();
						startIncompleteElement(element);
					}
					if (a7_0 != null) {
						if (a7_0 != null) {
							Object value = a7_0;
							addObjectToList(element, org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__CLASSIFIERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_2_0_0_6_0_0_0, a7_0, true);
						copyLocalizationInfos(a7_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 458);
				addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 459, 510);
				addExpectedElement(null, 511);
			}
			
			(
				(
					a8 = ';' {
						if (element == null) {
							element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createCompilationUnit();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_2_0_0_6_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a8, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(null, 512);
						addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 513, 564);
						addExpectedElement(null, 565);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 566);
				addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 567, 618);
				addExpectedElement(null, 619);
			}
			
		)
		
	)+	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 620, 671);
		addExpectedElement(null, 672);
	}
	
	(
		(
			a9 = '\u001a' {
				if (element == null) {
					element = org.emftext.language.java.containers.ContainersFactory.eINSTANCE.createCompilationUnit();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_2_0_0_7_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a9, element);
			}
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
	}
	
;

parse_org_emftext_language_java_imports_ClassifierImport returns [org.emftext.language.java.imports.ClassifierImport element = null]
@init{
}
:
	a0 = 'import' {
		if (element == null) {
			element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createClassifierImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_3_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 673, 674);
	}
	
	(
		(
			(
				a1 = IDENTIFIER				
				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createClassifierImport();
						startIncompleteElement(element);
					}
					if (a1 != null) {
						org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
						tokenResolver.setOptions(getOptions());
						org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__NAMESPACES), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
						}
						java.lang.String resolved = (java.lang.String) resolvedObject;
						if (resolved != null) {
							Object value = resolved;
							addObjectToList(element, org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__NAMESPACES, value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_3_0_0_1_0_0_0, resolved, true);
						copyLocalizationInfos((CommonToken) a1, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 675);
			}
			
			a2 = '.' {
				if (element == null) {
					element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createClassifierImport();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_3_0_0_1_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 676, 677);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, 678, 679);
	}
	
	(
		a3 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createClassifierImport();
				startIncompleteElement(element);
			}
			if (a3 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__CLASSIFIER), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a3).getLine(), ((CommonToken) a3).getCharPositionInLine(), ((CommonToken) a3).getStartIndex(), ((CommonToken) a3).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.emftext.language.java.classifiers.ConcreteClassifier proxy = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.emftext.language.java.resource.java.mopp.JavaContextDependentURIFragmentFactory<org.emftext.language.java.imports.ClassifierImport, org.emftext.language.java.classifiers.ConcreteClassifier>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getClassifierImportClassifierReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__CLASSIFIER), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__CLASSIFIER), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_3_0_0_2, proxy, true);
				copyLocalizationInfos((CommonToken) a3, element);
				copyLocalizationInfos((CommonToken) a3, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 680);
	}
	
	a4 = ';' {
		if (element == null) {
			element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createClassifierImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_3_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 681);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 682, 685);
		addExpectedElement(null, 686);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 687, 690);
		addExpectedElement(null, 691);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 692, 743);
	}
	
;

parse_org_emftext_language_java_imports_PackageImport returns [org.emftext.language.java.imports.PackageImport element = null]
@init{
}
:
	a0 = 'import' {
		if (element == null) {
			element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createPackageImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_4_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 744);
	}
	
	(
		(
			(
				a1 = IDENTIFIER				
				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createPackageImport();
						startIncompleteElement(element);
					}
					if (a1 != null) {
						org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
						tokenResolver.setOptions(getOptions());
						org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.PACKAGE_IMPORT__NAMESPACES), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
						}
						java.lang.String resolved = (java.lang.String) resolvedObject;
						if (resolved != null) {
							Object value = resolved;
							addObjectToList(element, org.emftext.language.java.imports.ImportsPackage.PACKAGE_IMPORT__NAMESPACES, value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_4_0_0_1_0_0_0, resolved, true);
						copyLocalizationInfos((CommonToken) a1, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 745);
			}
			
			a2 = '.' {
				if (element == null) {
					element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createPackageImport();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_4_0_0_1_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 746, 747);
			}
			
		)
		
	)+	{
		// expected elements (follow set)
		addExpectedElement(null, 748, 749);
	}
	
	a3 = '*' {
		if (element == null) {
			element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createPackageImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_4_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 750);
	}
	
	a4 = ';' {
		if (element == null) {
			element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createPackageImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_4_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 751);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 752, 755);
		addExpectedElement(null, 756);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 757, 760);
		addExpectedElement(null, 761);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 762, 813);
	}
	
;

parse_org_emftext_language_java_imports_StaticMemberImport returns [org.emftext.language.java.imports.StaticMemberImport element = null]
@init{
}
:
	a0 = 'import' {
		if (element == null) {
			element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticMemberImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_5_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticMemberImport(), 814);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_modifiers_Static		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticMemberImport();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_5_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 815, 816);
	}
	
	(
		(
			(
				a2 = IDENTIFIER				
				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticMemberImport();
						startIncompleteElement(element);
					}
					if (a2 != null) {
						org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
						tokenResolver.setOptions(getOptions());
						org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__NAMESPACES), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
						}
						java.lang.String resolved = (java.lang.String) resolvedObject;
						if (resolved != null) {
							Object value = resolved;
							addObjectToList(element, org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__NAMESPACES, value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_5_0_0_2_0_0_0, resolved, true);
						copyLocalizationInfos((CommonToken) a2, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 817);
			}
			
			a3 = '.' {
				if (element == null) {
					element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticMemberImport();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_5_0_0_2_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 818, 819);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, 820, 821);
	}
	
	(
		a4 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticMemberImport();
				startIncompleteElement(element);
			}
			if (a4 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a4.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC_MEMBERS), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a4).getLine(), ((CommonToken) a4).getCharPositionInLine(), ((CommonToken) a4).getStartIndex(), ((CommonToken) a4).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.emftext.language.java.references.ReferenceableElement proxy = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.emftext.language.java.resource.java.mopp.JavaContextDependentURIFragmentFactory<org.emftext.language.java.imports.StaticMemberImport, org.emftext.language.java.references.ReferenceableElement>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getStaticMemberImportStaticMembersReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC_MEMBERS), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					addObjectToList(element, org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC_MEMBERS, value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_5_0_0_3, proxy, true);
				copyLocalizationInfos((CommonToken) a4, element);
				copyLocalizationInfos((CommonToken) a4, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 822);
	}
	
	a5 = ';' {
		if (element == null) {
			element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticMemberImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_5_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 823);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 824, 827);
		addExpectedElement(null, 828);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 829, 832);
		addExpectedElement(null, 833);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 834, 885);
	}
	
;

parse_org_emftext_language_java_imports_StaticClassifierImport returns [org.emftext.language.java.imports.StaticClassifierImport element = null]
@init{
}
:
	a0 = 'import' {
		if (element == null) {
			element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticClassifierImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_6_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticClassifierImport(), 886);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_modifiers_Static		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticClassifierImport();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__STATIC), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_6_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 887);
	}
	
	(
		(
			(
				a2 = IDENTIFIER				
				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticClassifierImport();
						startIncompleteElement(element);
					}
					if (a2 != null) {
						org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
						tokenResolver.setOptions(getOptions());
						org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__NAMESPACES), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
						}
						java.lang.String resolved = (java.lang.String) resolvedObject;
						if (resolved != null) {
							Object value = resolved;
							addObjectToList(element, org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__NAMESPACES, value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_6_0_0_2_0_0_0, resolved, true);
						copyLocalizationInfos((CommonToken) a2, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 888);
			}
			
			a3 = '.' {
				if (element == null) {
					element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticClassifierImport();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_6_0_0_2_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 889, 890);
			}
			
		)
		
	)+	{
		// expected elements (follow set)
		addExpectedElement(null, 891, 892);
	}
	
	a4 = '*' {
		if (element == null) {
			element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticClassifierImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_6_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 893);
	}
	
	a5 = ';' {
		if (element == null) {
			element = org.emftext.language.java.imports.ImportsFactory.eINSTANCE.createStaticClassifierImport();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_6_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 894);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 895, 898);
		addExpectedElement(null, 899);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 900, 903);
		addExpectedElement(null, 904);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 905, 956);
	}
	
;

parse_org_emftext_language_java_classifiers_Class returns [org.emftext.language.java.classifiers.Class element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 957, 968);
		addExpectedElement(null, 969);
	}
	
	a1 = 'class' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 970);
	}
	
	(
		a2 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
				startIncompleteElement(element);
			}
			if (a2 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_2, resolved, true);
				copyLocalizationInfos((CommonToken) a2, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 971, 974);
	}
	
	(
		(
			a3 = '<' {
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_3_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 975);
			}
			
			(
				a4_0 = parse_org_emftext_language_java_generics_TypeParameter				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__TYPE_PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_3_0_0_1, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 976, 977);
			}
			
			(
				(
					a5 = ',' {
						if (element == null) {
							element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_3_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a5, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 978);
					}
					
					(
						a6_0 = parse_org_emftext_language_java_generics_TypeParameter						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
								startIncompleteElement(element);
							}
							if (a6_0 != null) {
								if (a6_0 != null) {
									Object value = a6_0;
									addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__TYPE_PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_3_0_0_2_0_0_1, a6_0, true);
								copyLocalizationInfos(a6_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 979, 980);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 981, 982);
			}
			
			a7 = '>' {
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_3_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a7, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 983, 985);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 986, 988);
	}
	
	(
		(
			a8 = 'extends' {
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_4_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a8, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 989, 1000);
			}
			
			(
				a9_0 = parse_org_emftext_language_java_types_TypeReference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
						startIncompleteElement(element);
					}
					if (a9_0 != null) {
						if (a9_0 != null) {
							Object value = a9_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__EXTENDS), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_4_0_0_1, a9_0, true);
						copyLocalizationInfos(a9_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 1001, 1002);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 1003, 1004);
	}
	
	(
		(
			a10 = 'implements' {
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_5_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a10, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 1005, 1016);
			}
			
			(
				(
					a11_0 = parse_org_emftext_language_java_types_TypeReference					{
						if (terminateParsing) {
							throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
						}
						if (element == null) {
							element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
							startIncompleteElement(element);
						}
						if (a11_0 != null) {
							if (a11_0 != null) {
								Object value = a11_0;
								addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__IMPLEMENTS, value);
								completedElement(value, true);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_5_0_0_1_0_0_0, a11_0, true);
							copyLocalizationInfos(a11_0, element);
						}
					}
				)
				{
					// expected elements (follow set)
					addExpectedElement(null, 1017, 1018);
				}
				
				(
					(
						a12 = ',' {
							if (element == null) {
								element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
								startIncompleteElement(element);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_5_0_0_1_0_0_1_0_0_0, null, true);
							copyLocalizationInfos((CommonToken)a12, element);
						}
						{
							// expected elements (follow set)
							addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 1019, 1030);
						}
						
						(
							a13_0 = parse_org_emftext_language_java_types_TypeReference							{
								if (terminateParsing) {
									throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
								}
								if (element == null) {
									element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
									startIncompleteElement(element);
								}
								if (a13_0 != null) {
									if (a13_0 != null) {
										Object value = a13_0;
										addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__IMPLEMENTS, value);
										completedElement(value, true);
									}
									collectHiddenTokens(element);
									retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_5_0_0_1_0_0_1_0_0_1, a13_0, true);
									copyLocalizationInfos(a13_0, element);
								}
							}
						)
						{
							// expected elements (follow set)
							addExpectedElement(null, 1031, 1032);
						}
						
					)
					
				)*				{
					// expected elements (follow set)
					addExpectedElement(null, 1033, 1034);
				}
				
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 1035);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 1036);
	}
	
	a14 = '{' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_7, null, true);
		copyLocalizationInfos((CommonToken)a14, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 1037, 1214);
		addExpectedElement(null, 1215);
	}
	
	(
		(
			(
				a15_0 = parse_org_emftext_language_java_members_Member				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
						startIncompleteElement(element);
					}
					if (a15_0 != null) {
						if (a15_0 != null) {
							Object value = a15_0;
							addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__MEMBERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_8_0_0_1, a15_0, true);
						copyLocalizationInfos(a15_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 1216, 1393);
				addExpectedElement(null, 1394);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 1395, 1572);
		addExpectedElement(null, 1573);
	}
	
	a16 = '}' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_7_0_0_10, null, true);
		copyLocalizationInfos((CommonToken)a16, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 1574);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 1575, 1626);
		addExpectedElement(null, 1627);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 1628, 1805);
		addExpectedElement(null, 1806);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 1807, 1984);
		addExpectedElement(null, 1985);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 1986, 2163);
		addExpectedElement(null, 2164);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 2165, 2342);
		addExpectedElement(null, 2343);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 2344, 2521);
		addExpectedElement(null, 2522);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 2523, 2709);
		addExpectedElement(null, 2710);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 2711, 2897);
		addExpectedElement(null, 2898);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 2899, 3085);
		addExpectedElement(null, 3086, 3088);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 3089, 3275);
		addExpectedElement(null, 3276);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 3277, 3463);
		addExpectedElement(null, 3464);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 3465, 3651);
		addExpectedElement(null, 3652);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 3653, 3839);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 3840, 3841);
		addExpectedElement(null, 3842);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 3843, 4029);
	}
	
;

parse_org_emftext_language_java_classifiers_AnonymousClass returns [org.emftext.language.java.classifiers.AnonymousClass element = null]
@init{
}
:
	a0 = '{' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createAnonymousClass();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_8_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 4030, 4207);
		addExpectedElement(null, 4208);
	}
	
	(
		(
			(
				a1_0 = parse_org_emftext_language_java_members_Member				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createAnonymousClass();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.ANONYMOUS_CLASS__MEMBERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_8_0_0_2_0_0_1, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 4209, 4386);
				addExpectedElement(null, 4387);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 4388, 4565);
		addExpectedElement(null, 4566);
	}
	
	a2 = '}' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createAnonymousClass();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_8_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 4567, 4571);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 4572, 4573);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 4574, 4576);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 4577, 4578);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 4579, 4581);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 4582, 4585);
		addExpectedElement(null, 4586);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 4587, 4588);
		addExpectedElement(null, 4589, 4597);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 4598, 4609);
		addExpectedElement(null, 4610, 4633);
	}
	
;

parse_org_emftext_language_java_classifiers_Interface returns [org.emftext.language.java.classifiers.Interface element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 4634, 4645);
		addExpectedElement(null, 4646);
	}
	
	a1 = 'interface' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 4647);
	}
	
	(
		a2 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
				startIncompleteElement(element);
			}
			if (a2 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_2, resolved, true);
				copyLocalizationInfos((CommonToken) a2, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 4648, 4650);
	}
	
	(
		(
			a3 = '<' {
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_3_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 4651);
			}
			
			(
				a4_0 = parse_org_emftext_language_java_generics_TypeParameter				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__TYPE_PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_3_0_0_1, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 4652, 4653);
			}
			
			(
				(
					a5 = ',' {
						if (element == null) {
							element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_3_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a5, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 4654);
					}
					
					(
						a6_0 = parse_org_emftext_language_java_generics_TypeParameter						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
								startIncompleteElement(element);
							}
							if (a6_0 != null) {
								if (a6_0 != null) {
									Object value = a6_0;
									addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__TYPE_PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_3_0_0_2_0_0_1, a6_0, true);
								copyLocalizationInfos(a6_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 4655, 4656);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 4657, 4658);
			}
			
			a7 = '>' {
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_3_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a7, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 4659, 4660);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 4661, 4662);
	}
	
	(
		(
			a8 = 'extends' {
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_4_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a8, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 4663, 4674);
			}
			
			(
				(
					a9_0 = parse_org_emftext_language_java_types_TypeReference					{
						if (terminateParsing) {
							throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
						}
						if (element == null) {
							element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
							startIncompleteElement(element);
						}
						if (a9_0 != null) {
							if (a9_0 != null) {
								Object value = a9_0;
								addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__EXTENDS, value);
								completedElement(value, true);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_4_0_0_1_0_0_0, a9_0, true);
							copyLocalizationInfos(a9_0, element);
						}
					}
				)
				{
					// expected elements (follow set)
					addExpectedElement(null, 4675, 4676);
				}
				
				(
					(
						a10 = ',' {
							if (element == null) {
								element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
								startIncompleteElement(element);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_4_0_0_1_0_0_1_0_0_0, null, true);
							copyLocalizationInfos((CommonToken)a10, element);
						}
						{
							// expected elements (follow set)
							addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 4677, 4688);
						}
						
						(
							a11_0 = parse_org_emftext_language_java_types_TypeReference							{
								if (terminateParsing) {
									throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
								}
								if (element == null) {
									element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
									startIncompleteElement(element);
								}
								if (a11_0 != null) {
									if (a11_0 != null) {
										Object value = a11_0;
										addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__EXTENDS, value);
										completedElement(value, true);
									}
									collectHiddenTokens(element);
									retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_4_0_0_1_0_0_1_0_0_1, a11_0, true);
									copyLocalizationInfos(a11_0, element);
								}
							}
						)
						{
							// expected elements (follow set)
							addExpectedElement(null, 4689, 4690);
						}
						
					)
					
				)*				{
					// expected elements (follow set)
					addExpectedElement(null, 4691, 4692);
				}
				
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 4693);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 4694);
	}
	
	a12 = '{' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a12, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 4695, 4872);
		addExpectedElement(null, 4873);
	}
	
	(
		(
			(
				a13_0 = parse_org_emftext_language_java_members_Member				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
						startIncompleteElement(element);
					}
					if (a13_0 != null) {
						if (a13_0 != null) {
							Object value = a13_0;
							addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__MEMBERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_7_0_0_1, a13_0, true);
						copyLocalizationInfos(a13_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 4874, 5051);
				addExpectedElement(null, 5052);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 5053, 5230);
		addExpectedElement(null, 5231);
	}
	
	a14 = '}' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createInterface();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_9_0_0_9, null, true);
		copyLocalizationInfos((CommonToken)a14, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 5232);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 5233, 5284);
		addExpectedElement(null, 5285);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 5286, 5463);
		addExpectedElement(null, 5464);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 5465, 5642);
		addExpectedElement(null, 5643);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 5644, 5821);
		addExpectedElement(null, 5822);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 5823, 6000);
		addExpectedElement(null, 6001);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 6002, 6179);
		addExpectedElement(null, 6180);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 6181, 6367);
		addExpectedElement(null, 6368);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 6369, 6555);
		addExpectedElement(null, 6556);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 6557, 6743);
		addExpectedElement(null, 6744, 6746);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 6747, 6933);
		addExpectedElement(null, 6934);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 6935, 7121);
		addExpectedElement(null, 7122);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 7123, 7309);
		addExpectedElement(null, 7310);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 7311, 7497);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 7498, 7499);
		addExpectedElement(null, 7500);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 7501, 7687);
	}
	
;

parse_org_emftext_language_java_classifiers_Enumeration returns [org.emftext.language.java.classifiers.Enumeration element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 7688, 7699);
		addExpectedElement(null, 7700);
	}
	
	a1 = 'enum' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 7701);
	}
	
	(
		a2 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
				startIncompleteElement(element);
			}
			if (a2 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a2.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a2).getLine(), ((CommonToken) a2).getCharPositionInLine(), ((CommonToken) a2).getStartIndex(), ((CommonToken) a2).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_2, resolved, true);
				copyLocalizationInfos((CommonToken) a2, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 7702, 7703);
	}
	
	(
		(
			a3 = 'implements' {
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_3_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 7704, 7715);
			}
			
			(
				(
					a4_0 = parse_org_emftext_language_java_types_TypeReference					{
						if (terminateParsing) {
							throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
						}
						if (element == null) {
							element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
							startIncompleteElement(element);
						}
						if (a4_0 != null) {
							if (a4_0 != null) {
								Object value = a4_0;
								addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__IMPLEMENTS, value);
								completedElement(value, true);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_3_0_0_1_0_0_0, a4_0, true);
							copyLocalizationInfos(a4_0, element);
						}
					}
				)
				{
					// expected elements (follow set)
					addExpectedElement(null, 7716, 7717);
				}
				
				(
					(
						a5 = ',' {
							if (element == null) {
								element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
								startIncompleteElement(element);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_3_0_0_1_0_0_1_0_0_0, null, true);
							copyLocalizationInfos((CommonToken)a5, element);
						}
						{
							// expected elements (follow set)
							addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 7718, 7729);
						}
						
						(
							a6_0 = parse_org_emftext_language_java_types_TypeReference							{
								if (terminateParsing) {
									throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
								}
								if (element == null) {
									element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
									startIncompleteElement(element);
								}
								if (a6_0 != null) {
									if (a6_0 != null) {
										Object value = a6_0;
										addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__IMPLEMENTS, value);
										completedElement(value, true);
									}
									collectHiddenTokens(element);
									retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_3_0_0_1_0_0_1_0_0_1, a6_0, true);
									copyLocalizationInfos(a6_0, element);
								}
							}
						)
						{
							// expected elements (follow set)
							addExpectedElement(null, 7730, 7731);
						}
						
					)
					
				)*				{
					// expected elements (follow set)
					addExpectedElement(null, 7732, 7733);
				}
				
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 7734);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 7735);
	}
	
	a7 = '{' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_5, null, true);
		copyLocalizationInfos((CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 7736, 7737);
		addExpectedElement(null, 7738, 7740);
	}
	
	(
		(
			(
				a8_0 = parse_org_emftext_language_java_members_EnumConstant				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
						startIncompleteElement(element);
					}
					if (a8_0 != null) {
						if (a8_0 != null) {
							Object value = a8_0;
							addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__CONSTANTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_6_0_0_1, a8_0, true);
						copyLocalizationInfos(a8_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 7741, 7744);
			}
			
			(
				(
					a9 = ',' {
						if (element == null) {
							element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_6_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a9, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 7745, 7746);
					}
					
					(
						a10_0 = parse_org_emftext_language_java_members_EnumConstant						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
								startIncompleteElement(element);
							}
							if (a10_0 != null) {
								if (a10_0 != null) {
									Object value = a10_0;
									addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__CONSTANTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_6_0_0_2_0_0_2, a10_0, true);
								copyLocalizationInfos(a10_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 7747, 7750);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 7751, 7754);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 7755, 7757);
	}
	
	(
		(
			a11 = ',' {
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_7_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a11, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 7758, 7759);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 7760, 7761);
	}
	
	(
		(
			a12 = ';' {
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_8_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a12, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 7762, 7939);
				addExpectedElement(null, 7940);
			}
			
			(
				(
					(
						a13_0 = parse_org_emftext_language_java_members_Member						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
								startIncompleteElement(element);
							}
							if (a13_0 != null) {
								if (a13_0 != null) {
									Object value = a13_0;
									addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__MEMBERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_8_0_0_1_0_0_1, a13_0, true);
								copyLocalizationInfos(a13_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 7941, 8118);
						addExpectedElement(null, 8119);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 8120, 8297);
				addExpectedElement(null, 8298);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 8299);
	}
	
	a14 = '}' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createEnumeration();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_10_0_0_9, null, true);
		copyLocalizationInfos((CommonToken)a14, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 8300);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 8301, 8352);
		addExpectedElement(null, 8353);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 8354, 8531);
		addExpectedElement(null, 8532);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 8533, 8710);
		addExpectedElement(null, 8711);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 8712, 8889);
		addExpectedElement(null, 8890);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 8891, 9068);
		addExpectedElement(null, 9069);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 9070, 9247);
		addExpectedElement(null, 9248);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 9249, 9435);
		addExpectedElement(null, 9436);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 9437, 9623);
		addExpectedElement(null, 9624);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 9625, 9811);
		addExpectedElement(null, 9812, 9814);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 9815, 10001);
		addExpectedElement(null, 10002);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 10003, 10189);
		addExpectedElement(null, 10190);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 10191, 10377);
		addExpectedElement(null, 10378);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 10379, 10565);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 10566, 10567);
		addExpectedElement(null, 10568);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 10569, 10755);
	}
	
;

parse_org_emftext_language_java_classifiers_Annotation returns [org.emftext.language.java.classifiers.Annotation element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createAnnotation();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_11_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 10756, 10767);
		addExpectedElement(null, 10768);
	}
	
	a1 = '@' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createAnnotation();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_11_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 10769);
	}
	
	a2 = 'interface' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createAnnotation();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_11_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 10770);
	}
	
	(
		a3 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createAnnotation();
				startIncompleteElement(element);
			}
			if (a3 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a3).getLine(), ((CommonToken) a3).getCharPositionInLine(), ((CommonToken) a3).getStartIndex(), ((CommonToken) a3).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_11_0_0_3, resolved, true);
				copyLocalizationInfos((CommonToken) a3, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 10771);
	}
	
	a4 = '{' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createAnnotation();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_11_0_0_5, null, true);
		copyLocalizationInfos((CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 10772, 10949);
		addExpectedElement(null, 10950);
	}
	
	(
		(
			(
				a5_0 = parse_org_emftext_language_java_members_Member				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createAnnotation();
						startIncompleteElement(element);
					}
					if (a5_0 != null) {
						if (a5_0 != null) {
							Object value = a5_0;
							addObjectToList(element, org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__MEMBERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_11_0_0_6_0_0_1, a5_0, true);
						copyLocalizationInfos(a5_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 10951, 11128);
				addExpectedElement(null, 11129);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 11130, 11307);
		addExpectedElement(null, 11308);
	}
	
	a6 = '}' {
		if (element == null) {
			element = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createAnnotation();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_11_0_0_8, null, true);
		copyLocalizationInfos((CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 11309);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), 11310, 11361);
		addExpectedElement(null, 11362);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 11363, 11540);
		addExpectedElement(null, 11541);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 11542, 11719);
		addExpectedElement(null, 11720);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 11721, 11898);
		addExpectedElement(null, 11899);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 11900, 12077);
		addExpectedElement(null, 12078);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 12079, 12256);
		addExpectedElement(null, 12257);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 12258, 12444);
		addExpectedElement(null, 12445);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 12446, 12632);
		addExpectedElement(null, 12633);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 12634, 12820);
		addExpectedElement(null, 12821, 12823);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 12824, 13010);
		addExpectedElement(null, 13011);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 13012, 13198);
		addExpectedElement(null, 13199);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 13200, 13386);
		addExpectedElement(null, 13387);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 13388, 13574);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 13575, 13576);
		addExpectedElement(null, 13577);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 13578, 13764);
	}
	
;

parse_org_emftext_language_java_annotations_AnnotationInstance returns [org.emftext.language.java.annotations.AnnotationInstance element = null]
@init{
}
:
	a0 = '@' {
		if (element == null) {
			element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationInstance();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_12_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 13765, 13766);
	}
	
	(
		(
			(
				a1 = IDENTIFIER				
				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationInstance();
						startIncompleteElement(element);
					}
					if (a1 != null) {
						org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
						tokenResolver.setOptions(getOptions());
						org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__NAMESPACES), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
						}
						java.lang.String resolved = (java.lang.String) resolvedObject;
						if (resolved != null) {
							Object value = resolved;
							addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__NAMESPACES, value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_12_0_0_1_0_0_0, resolved, true);
						copyLocalizationInfos((CommonToken) a1, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 13767);
			}
			
			a2 = '.' {
				if (element == null) {
					element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationInstance();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_12_0_0_1_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 13768, 13769);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, 13770, 13771);
	}
	
	(
		a3 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationInstance();
				startIncompleteElement(element);
			}
			if (a3 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a3.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__ANNOTATION), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a3).getLine(), ((CommonToken) a3).getCharPositionInLine(), ((CommonToken) a3).getStartIndex(), ((CommonToken) a3).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.emftext.language.java.classifiers.Classifier proxy = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.emftext.language.java.resource.java.mopp.JavaContextDependentURIFragmentFactory<org.emftext.language.java.annotations.AnnotationInstance, org.emftext.language.java.classifiers.Classifier>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getAnnotationInstanceAnnotationReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__ANNOTATION), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__ANNOTATION), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_12_0_0_2, proxy, true);
				copyLocalizationInfos((CommonToken) a3, element);
				copyLocalizationInfos((CommonToken) a3, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance(), 13772, 13773);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 13774);
		addExpectedElement(null, 13775);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 13776, 13787);
		addExpectedElement(null, 13788);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 13789, 13800);
		addExpectedElement(null, 13801);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 13802, 13813);
		addExpectedElement(null, 13814);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 13815, 13826);
		addExpectedElement(null, 13827);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), 13828);
		addExpectedElement(null, 13829);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 13830, 13841);
		addExpectedElement(null, 13842, 13843);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 13844, 13855);
		addExpectedElement(null, 13856);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 13857, 13868);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 13869, 13880);
		addExpectedElement(null, 13881);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 13882, 13893);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 13894, 13905);
		addExpectedElement(null, 13906);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 13907, 13918);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 13919, 13942);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 13943, 13966);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 13967, 13990);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 13991, 14014);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 14015, 14016);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 14017, 14019);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 14020, 14021);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 14022, 14024);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 14025, 14028);
		addExpectedElement(null, 14029);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 14030, 14031);
		addExpectedElement(null, 14032, 14040);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 14041, 14052);
		addExpectedElement(null, 14053, 14071);
	}
	
	(
		(
			(
				a4_0 = parse_org_emftext_language_java_annotations_AnnotationParameter				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationInstance();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__PARAMETER), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_12_0_0_3_0_0_0, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 14072);
				addExpectedElement(null, 14073);
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 14074, 14085);
				addExpectedElement(null, 14086);
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 14087, 14098);
				addExpectedElement(null, 14099);
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 14100, 14111);
				addExpectedElement(null, 14112);
				addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 14113, 14124);
				addExpectedElement(null, 14125);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), 14126);
				addExpectedElement(null, 14127);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 14128, 14139);
				addExpectedElement(null, 14140, 14141);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 14142, 14153);
				addExpectedElement(null, 14154);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 14155, 14166);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 14167, 14178);
				addExpectedElement(null, 14179);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 14180, 14191);
				addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 14192, 14203);
				addExpectedElement(null, 14204);
				addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 14205, 14216);
				addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 14217, 14240);
				addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 14241, 14264);
				addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 14265, 14288);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 14289, 14312);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 14313, 14314);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 14315, 14317);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 14318, 14319);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 14320, 14322);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 14323, 14326);
				addExpectedElement(null, 14327);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 14328, 14329);
				addExpectedElement(null, 14330, 14338);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 14339, 14350);
				addExpectedElement(null, 14351, 14369);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 14370);
		addExpectedElement(null, 14371);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 14372, 14383);
		addExpectedElement(null, 14384);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 14385, 14396);
		addExpectedElement(null, 14397);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 14398, 14409);
		addExpectedElement(null, 14410);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 14411, 14422);
		addExpectedElement(null, 14423);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), 14424);
		addExpectedElement(null, 14425);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 14426, 14437);
		addExpectedElement(null, 14438, 14439);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 14440, 14451);
		addExpectedElement(null, 14452);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 14453, 14464);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 14465, 14476);
		addExpectedElement(null, 14477);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 14478, 14489);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 14490, 14501);
		addExpectedElement(null, 14502);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 14503, 14514);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 14515, 14538);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 14539, 14562);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 14563, 14586);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 14587, 14610);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 14611, 14612);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 14613, 14615);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 14616, 14617);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 14618, 14620);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 14621, 14624);
		addExpectedElement(null, 14625);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 14626, 14627);
		addExpectedElement(null, 14628, 14636);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 14637, 14648);
		addExpectedElement(null, 14649, 14667);
	}
	
;

parse_org_emftext_language_java_annotations_SingleAnnotationParameter returns [org.emftext.language.java.annotations.SingleAnnotationParameter element = null]
@init{
}
:
	a0 = '(' {
		if (element == null) {
			element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createSingleAnnotationParameter();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_13_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getSingleAnnotationParameter(), 14668, 14752);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_arrays_ArrayInitializer		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createSingleAnnotationParameter();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.SINGLE_ANNOTATION_PARAMETER__VALUE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_13_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
		|		a1_1 = parse_org_emftext_language_java_expressions_ConditionalExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createSingleAnnotationParameter();
				startIncompleteElement(element);
			}
			if (a1_1 != null) {
				if (a1_1 != null) {
					Object value = a1_1;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.SINGLE_ANNOTATION_PARAMETER__VALUE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_13_0_0_1, a1_1, true);
				copyLocalizationInfos(a1_1, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 14753);
	}
	
	a2 = ')' {
		if (element == null) {
			element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createSingleAnnotationParameter();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_13_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 14754);
		addExpectedElement(null, 14755);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 14756, 14767);
		addExpectedElement(null, 14768);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 14769, 14780);
		addExpectedElement(null, 14781);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 14782, 14793);
		addExpectedElement(null, 14794);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 14795, 14806);
		addExpectedElement(null, 14807);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), 14808);
		addExpectedElement(null, 14809);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 14810, 14821);
		addExpectedElement(null, 14822, 14823);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 14824, 14835);
		addExpectedElement(null, 14836);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 14837, 14848);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 14849, 14860);
		addExpectedElement(null, 14861);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 14862, 14873);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 14874, 14885);
		addExpectedElement(null, 14886);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 14887, 14898);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 14899, 14922);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 14923, 14946);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 14947, 14970);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 14971, 14994);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 14995, 14996);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 14997, 14999);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 15000, 15001);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 15002, 15004);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 15005, 15008);
		addExpectedElement(null, 15009);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 15010, 15011);
		addExpectedElement(null, 15012, 15020);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 15021, 15032);
		addExpectedElement(null, 15033, 15051);
	}
	
;

parse_org_emftext_language_java_annotations_AnnotationParameterList returns [org.emftext.language.java.annotations.AnnotationParameterList element = null]
@init{
}
:
	a0 = '(' {
		if (element == null) {
			element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationParameterList();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_14_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationParameterList(), 15052);
		addExpectedElement(null, 15053);
	}
	
	(
		(
			(
				a1_0 = parse_org_emftext_language_java_annotations_AnnotationAttributeSetting				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationParameterList();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_PARAMETER_LIST__SETTINGS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_14_0_0_1_0_0_0, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 15054, 15055);
			}
			
			(
				(
					a2 = ',' {
						if (element == null) {
							element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationParameterList();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_14_0_0_1_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a2, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationParameterList(), 15056);
					}
					
					(
						a3_0 = parse_org_emftext_language_java_annotations_AnnotationAttributeSetting						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationParameterList();
								startIncompleteElement(element);
							}
							if (a3_0 != null) {
								if (a3_0 != null) {
									Object value = a3_0;
									addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_PARAMETER_LIST__SETTINGS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_14_0_0_1_0_0_1_0_0_1, a3_0, true);
								copyLocalizationInfos(a3_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 15057, 15058);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 15059, 15060);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 15061);
	}
	
	a4 = ')' {
		if (element == null) {
			element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationParameterList();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_14_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), 15062);
		addExpectedElement(null, 15063);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 15064, 15075);
		addExpectedElement(null, 15076);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 15077, 15088);
		addExpectedElement(null, 15089);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 15090, 15101);
		addExpectedElement(null, 15102);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 15103, 15114);
		addExpectedElement(null, 15115);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), 15116);
		addExpectedElement(null, 15117);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 15118, 15129);
		addExpectedElement(null, 15130, 15131);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 15132, 15143);
		addExpectedElement(null, 15144);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 15145, 15156);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 15157, 15168);
		addExpectedElement(null, 15169);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 15170, 15181);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 15182, 15193);
		addExpectedElement(null, 15194);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 15195, 15206);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 15207, 15230);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 15231, 15254);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 15255, 15278);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 15279, 15302);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 15303, 15304);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 15305, 15307);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 15308, 15309);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 15310, 15312);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 15313, 15316);
		addExpectedElement(null, 15317);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 15318, 15319);
		addExpectedElement(null, 15320, 15328);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 15329, 15340);
		addExpectedElement(null, 15341, 15359);
	}
	
;

parse_org_emftext_language_java_annotations_AnnotationAttributeSetting returns [org.emftext.language.java.annotations.AnnotationAttributeSetting element = null]
@init{
}
:
	(
		a0 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttributeSetting();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.emftext.language.java.members.InterfaceMethod proxy = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.emftext.language.java.resource.java.mopp.JavaContextDependentURIFragmentFactory<org.emftext.language.java.annotations.AnnotationAttributeSetting, org.emftext.language.java.members.InterfaceMethod>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getAnnotationAttributeSettingAttributeReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_15_0_0_0, proxy, true);
				copyLocalizationInfos((CommonToken) a0, element);
				copyLocalizationInfos((CommonToken) a0, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 15360);
	}
	
	a1 = '=' {
		if (element == null) {
			element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttributeSetting();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_15_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttributeSetting(), 15361, 15445);
	}
	
	(
		a2_0 = parse_org_emftext_language_java_arrays_ArrayInitializer		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttributeSetting();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__VALUE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_15_0_0_4, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
		|		a2_1 = parse_org_emftext_language_java_expressions_ConditionalExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttributeSetting();
				startIncompleteElement(element);
			}
			if (a2_1 != null) {
				if (a2_1 != null) {
					Object value = a2_1;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__VALUE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_15_0_0_4, a2_1, true);
				copyLocalizationInfos(a2_1, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 15446, 15447);
	}
	
;

parse_org_emftext_language_java_generics_TypeParameter returns [org.emftext.language.java.generics.TypeParameter element = null]
@init{
}
:
	(
		a0 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createTypeParameter();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_16_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 15448, 15460);
	}
	
	(
		(
			a1 = 'extends' {
				if (element == null) {
					element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createTypeParameter();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_16_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), 15461, 15472);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_types_TypeReference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createTypeParameter();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_16_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 15473, 15485);
			}
			
			(
				(
					a3 = '&' {
						if (element == null) {
							element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createTypeParameter();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_16_0_0_1_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a3, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), 15486, 15497);
					}
					
					(
						a4_0 = parse_org_emftext_language_java_types_TypeReference						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createTypeParameter();
								startIncompleteElement(element);
							}
							if (a4_0 != null) {
								if (a4_0 != null) {
									Object value = a4_0;
									addObjectToList(element, org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_16_0_0_1_0_0_2_0_0_1, a4_0, true);
								copyLocalizationInfos(a4_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						// We've found the last token for this rule. The constructed EObject is now
						// complete.
						completedElement(element, true);
						addExpectedElement(null, 15498, 15510);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 15511, 15523);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 15524, 15535);
	}
	
;

parse_org_emftext_language_java_members_EnumConstant returns [org.emftext.language.java.members.EnumConstant element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_annotations_AnnotationInstance			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createEnumConstant();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ANNOTATIONS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_17_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), 15536);
		addExpectedElement(null, 15537);
	}
	
	(
		a1 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createEnumConstant();
				startIncompleteElement(element);
			}
			if (a1 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_17_0_0_1, resolved, true);
				copyLocalizationInfos((CommonToken) a1, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 15538);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), 15539);
		addExpectedElement(null, 15540, 15543);
	}
	
	(
		(
			a2 = '(' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createEnumConstant();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_17_0_0_2_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), 15544, 15627);
				addExpectedElement(null, 15628);
			}
			
			(
				(
					(
						a3_0 = parse_org_emftext_language_java_expressions_AssignmentExpression						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createEnumConstant();
								startIncompleteElement(element);
							}
							if (a3_0 != null) {
								if (a3_0 != null) {
									Object value = a3_0;
									addObjectToList(element, org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_17_0_0_2_0_0_2_0_0_0, a3_0, true);
								copyLocalizationInfos(a3_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 15629, 15630);
					}
					
					(
						(
							a4 = ',' {
								if (element == null) {
									element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createEnumConstant();
									startIncompleteElement(element);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_17_0_0_2_0_0_2_0_0_1_0_0_0, null, true);
								copyLocalizationInfos((CommonToken)a4, element);
							}
							{
								// expected elements (follow set)
								addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), 15631, 15714);
							}
							
							(
								a5_0 = parse_org_emftext_language_java_expressions_AssignmentExpression								{
									if (terminateParsing) {
										throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
									}
									if (element == null) {
										element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createEnumConstant();
										startIncompleteElement(element);
									}
									if (a5_0 != null) {
										if (a5_0 != null) {
											Object value = a5_0;
											addObjectToList(element, org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ARGUMENTS, value);
											completedElement(value, true);
										}
										collectHiddenTokens(element);
										retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_17_0_0_2_0_0_2_0_0_1_0_0_1, a5_0, true);
										copyLocalizationInfos(a5_0, element);
									}
								}
							)
							{
								// expected elements (follow set)
								addExpectedElement(null, 15715, 15716);
							}
							
						)
						
					)*					{
						// expected elements (follow set)
						addExpectedElement(null, 15717, 15718);
					}
					
				)
				
			)?			{
				// expected elements (follow set)
				addExpectedElement(null, 15719);
			}
			
			a6 = ')' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createEnumConstant();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_17_0_0_2_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a6, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), 15720);
				addExpectedElement(null, 15721, 15724);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), 15725);
		addExpectedElement(null, 15726, 15729);
	}
	
	(
		(
			(
				a7_0 = parse_org_emftext_language_java_classifiers_AnonymousClass				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createEnumConstant();
						startIncompleteElement(element);
					}
					if (a7_0 != null) {
						if (a7_0 != null) {
							Object value = a7_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ANONYMOUS_CLASS), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_17_0_0_3_0_0_0, a7_0, true);
						copyLocalizationInfos(a7_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 15730, 15733);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 15734, 15737);
	}
	
;

parse_org_emftext_language_java_statements_Block returns [org.emftext.language.java.statements.Block element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_Modifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createBlock();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.statements.StatementsPackage.BLOCK__MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_18_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 15738, 15748);
		addExpectedElement(null, 15749);
	}
	
	a1 = '{' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_18_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 15750, 15936);
		addExpectedElement(null, 15937);
	}
	
	(
		(
			(
				a2_0 = parse_org_emftext_language_java_statements_Statement				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createBlock();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.statements.StatementsPackage.BLOCK__STATEMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_18_0_0_3_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 15938, 16124);
				addExpectedElement(null, 16125);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 16126, 16312);
		addExpectedElement(null, 16313);
	}
	
	a3 = '}' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_18_0_0_5, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 16314, 16491);
		addExpectedElement(null, 16492);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 16493, 16670);
		addExpectedElement(null, 16671);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 16672, 16849);
		addExpectedElement(null, 16850);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 16851, 17028);
		addExpectedElement(null, 17029);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 17030, 17207);
		addExpectedElement(null, 17208);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 17209, 17395);
		addExpectedElement(null, 17396);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 17397, 17583);
		addExpectedElement(null, 17584);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 17585, 17771);
		addExpectedElement(null, 17772, 17774);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 17775, 17961);
		addExpectedElement(null, 17962);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 17963, 18149);
		addExpectedElement(null, 18150);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 18151, 18337);
		addExpectedElement(null, 18338);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 18339, 18525);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 18526, 18527);
		addExpectedElement(null, 18528);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 18529, 18715);
	}
	
;

parse_org_emftext_language_java_members_Constructor returns [org.emftext.language.java.members.Constructor element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 18716, 18727);
		addExpectedElement(null, 18728, 18729);
	}
	
	(
		(
			a1 = '<' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 18730);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_generics_TypeParameter				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 18731, 18732);
			}
			
			(
				(
					a3 = ',' {
						if (element == null) {
							element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_1_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a3, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 18733);
					}
					
					(
						a4_0 = parse_org_emftext_language_java_generics_TypeParameter						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
								startIncompleteElement(element);
							}
							if (a4_0 != null) {
								if (a4_0 != null) {
									Object value = a4_0;
									addObjectToList(element, org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_1_0_0_2_0_0_1, a4_0, true);
								copyLocalizationInfos(a4_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 18734, 18735);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 18736, 18737);
			}
			
			a5 = '>' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_1_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 18738);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 18739);
	}
	
	(
		a6 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
				startIncompleteElement(element);
			}
			if (a6 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a6.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a6).getLine(), ((CommonToken) a6).getCharPositionInLine(), ((CommonToken) a6).getStartIndex(), ((CommonToken) a6).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_2, resolved, true);
				copyLocalizationInfos((CommonToken) a6, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 18740);
	}
	
	a7 = '(' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a7, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 18741, 18788);
		addExpectedElement(null, 18789);
	}
	
	(
		(
			(
				a8_0 = parse_org_emftext_language_java_parameters_Parameter				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
						startIncompleteElement(element);
					}
					if (a8_0 != null) {
						if (a8_0 != null) {
							Object value = a8_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_4_0_0_0, a8_0, true);
						copyLocalizationInfos(a8_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 18790, 18791);
			}
			
			(
				(
					a9 = ',' {
						if (element == null) {
							element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_4_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a9, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 18792, 18839);
					}
					
					(
						a10_0 = parse_org_emftext_language_java_parameters_Parameter						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
								startIncompleteElement(element);
							}
							if (a10_0 != null) {
								if (a10_0 != null) {
									Object value = a10_0;
									addObjectToList(element, org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_4_0_0_1_0_0_1, a10_0, true);
								copyLocalizationInfos(a10_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 18840, 18841);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 18842, 18843);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 18844);
	}
	
	a11 = ')' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_5, null, true);
		copyLocalizationInfos((CommonToken)a11, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 18845, 18846);
	}
	
	(
		(
			a12 = 'throws' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_6_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a12, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 18847, 18848);
			}
			
			(
				a13_0 = parse_org_emftext_language_java_types_NamespaceClassifierReference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
						startIncompleteElement(element);
					}
					if (a13_0 != null) {
						if (a13_0 != null) {
							Object value = a13_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__EXCEPTIONS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_6_0_0_1, a13_0, true);
						copyLocalizationInfos(a13_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 18849, 18850);
			}
			
			(
				(
					a14 = ',' {
						if (element == null) {
							element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_6_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a14, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 18851, 18852);
					}
					
					(
						a15_0 = parse_org_emftext_language_java_types_NamespaceClassifierReference						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
								startIncompleteElement(element);
							}
							if (a15_0 != null) {
								if (a15_0 != null) {
									Object value = a15_0;
									addObjectToList(element, org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__EXCEPTIONS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_6_0_0_2_0_0_1, a15_0, true);
								copyLocalizationInfos(a15_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 18853, 18854);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 18855, 18856);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 18857);
	}
	
	a16 = '{' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_8, null, true);
		copyLocalizationInfos((CommonToken)a16, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 18858, 19044);
		addExpectedElement(null, 19045);
	}
	
	(
		(
			(
				a17_0 = parse_org_emftext_language_java_statements_Statement				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
						startIncompleteElement(element);
					}
					if (a17_0 != null) {
						if (a17_0 != null) {
							Object value = a17_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__STATEMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_9_0_0_1, a17_0, true);
						copyLocalizationInfos(a17_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 19046, 19232);
				addExpectedElement(null, 19233);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 19234, 19420);
		addExpectedElement(null, 19421);
	}
	
	a18 = '}' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createConstructor();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_19_0_0_11, null, true);
		copyLocalizationInfos((CommonToken)a18, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 19422, 19599);
		addExpectedElement(null, 19600);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 19601, 19778);
		addExpectedElement(null, 19779);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 19780, 19957);
		addExpectedElement(null, 19958);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 19959, 20136);
		addExpectedElement(null, 20137);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 20138, 20315);
		addExpectedElement(null, 20316);
	}
	
;

parse_org_emftext_language_java_members_InterfaceMethod returns [org.emftext.language.java.members.InterfaceMethod element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20317, 20328);
		addExpectedElement(null, 20329);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20330, 20341);
	}
	
	(
		(
			a1 = '<' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20342);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_generics_TypeParameter				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 20343, 20344);
			}
			
			(
				(
					a3 = ',' {
						if (element == null) {
							element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_1_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a3, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20345);
					}
					
					(
						a4_0 = parse_org_emftext_language_java_generics_TypeParameter						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
								startIncompleteElement(element);
							}
							if (a4_0 != null) {
								if (a4_0 != null) {
									Object value = a4_0;
									addObjectToList(element, org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_1_0_0_2_0_0_1, a4_0, true);
								copyLocalizationInfos(a4_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 20346, 20347);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 20348, 20349);
			}
			
			a5 = '>' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_1_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20350, 20361);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20362, 20373);
	}
	
	(
		(
			a6_0 = parse_org_emftext_language_java_types_TypeReference			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
					startIncompleteElement(element);
				}
				if (a6_0 != null) {
					if (a6_0 != null) {
						Object value = a6_0;
						element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_REFERENCE), value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_2_0_0_0, a6_0, true);
					copyLocalizationInfos(a6_0, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20374);
			addExpectedElement(null, 20375);
		}
		
		(
			(
				a7_0 = parse_org_emftext_language_java_arrays_ArrayDimension				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
						startIncompleteElement(element);
					}
					if (a7_0 != null) {
						if (a7_0 != null) {
							Object value = a7_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ARRAY_DIMENSIONS_BEFORE, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_2_0_0_1, a7_0, true);
						copyLocalizationInfos(a7_0, element);
					}
				}
			)
			
		)*		{
			// expected elements (follow set)
			addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20376);
			addExpectedElement(null, 20377);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 20378);
	}
	
	(
		a8 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
				startIncompleteElement(element);
			}
			if (a8 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a8.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a8).getLine(), ((CommonToken) a8).getCharPositionInLine(), ((CommonToken) a8).getStartIndex(), ((CommonToken) a8).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_3, resolved, true);
				copyLocalizationInfos((CommonToken) a8, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 20379);
	}
	
	a9 = '(' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a9, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20380, 20427);
		addExpectedElement(null, 20428);
	}
	
	(
		(
			(
				a10_0 = parse_org_emftext_language_java_parameters_Parameter				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
						startIncompleteElement(element);
					}
					if (a10_0 != null) {
						if (a10_0 != null) {
							Object value = a10_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_5_0_0_0, a10_0, true);
						copyLocalizationInfos(a10_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 20429, 20430);
			}
			
			(
				(
					a11 = ',' {
						if (element == null) {
							element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_5_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a11, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20431, 20478);
					}
					
					(
						a12_0 = parse_org_emftext_language_java_parameters_Parameter						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
								startIncompleteElement(element);
							}
							if (a12_0 != null) {
								if (a12_0 != null) {
									Object value = a12_0;
									addObjectToList(element, org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_5_0_0_1_0_0_1, a12_0, true);
								copyLocalizationInfos(a12_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 20479, 20480);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 20481, 20482);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 20483);
	}
	
	a13 = ')' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a13, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20484);
		addExpectedElement(null, 20485, 20486);
	}
	
	(
		(
			a14_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
					startIncompleteElement(element);
				}
				if (a14_0 != null) {
					if (a14_0 != null) {
						Object value = a14_0;
						addObjectToList(element, org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ARRAY_DIMENSIONS_AFTER, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_7, a14_0, true);
					copyLocalizationInfos(a14_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20487);
		addExpectedElement(null, 20488, 20489);
	}
	
	(
		(
			a15 = 'throws' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_8_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a15, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20490, 20491);
			}
			
			(
				a16_0 = parse_org_emftext_language_java_types_NamespaceClassifierReference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
						startIncompleteElement(element);
					}
					if (a16_0 != null) {
						if (a16_0 != null) {
							Object value = a16_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__EXCEPTIONS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_8_0_0_1, a16_0, true);
						copyLocalizationInfos(a16_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 20492, 20493);
			}
			
			(
				(
					a17 = ',' {
						if (element == null) {
							element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_8_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a17, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 20494, 20495);
					}
					
					(
						a18_0 = parse_org_emftext_language_java_types_NamespaceClassifierReference						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
								startIncompleteElement(element);
							}
							if (a18_0 != null) {
								if (a18_0 != null) {
									Object value = a18_0;
									addObjectToList(element, org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__EXCEPTIONS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_8_0_0_2_0_0_1, a18_0, true);
								copyLocalizationInfos(a18_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 20496, 20497);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 20498, 20499);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 20500);
	}
	
	a19 = ';' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createInterfaceMethod();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_20_0_0_9, null, true);
		copyLocalizationInfos((CommonToken)a19, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 20501, 20678);
		addExpectedElement(null, 20679);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 20680, 20857);
		addExpectedElement(null, 20858);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 20859, 21036);
		addExpectedElement(null, 21037);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 21038, 21215);
		addExpectedElement(null, 21216);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 21217, 21394);
		addExpectedElement(null, 21395);
	}
	
	|//derived choice rules for sub-classes: 
	
	c0 = parse_org_emftext_language_java_annotations_AnnotationAttribute{ element = c0; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_members_ClassMethod returns [org.emftext.language.java.members.ClassMethod element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21396, 21407);
		addExpectedElement(null, 21408);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21409, 21420);
	}
	
	(
		(
			a1 = '<' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21421);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_generics_TypeParameter				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 21422, 21423);
			}
			
			(
				(
					a3 = ',' {
						if (element == null) {
							element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_1_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a3, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21424);
					}
					
					(
						a4_0 = parse_org_emftext_language_java_generics_TypeParameter						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
								startIncompleteElement(element);
							}
							if (a4_0 != null) {
								if (a4_0 != null) {
									Object value = a4_0;
									addObjectToList(element, org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_1_0_0_2_0_0_1, a4_0, true);
								copyLocalizationInfos(a4_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 21425, 21426);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 21427, 21428);
			}
			
			a5 = '>' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_1_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21429, 21440);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21441, 21452);
	}
	
	(
		(
			a6_0 = parse_org_emftext_language_java_types_TypeReference			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
					startIncompleteElement(element);
				}
				if (a6_0 != null) {
					if (a6_0 != null) {
						Object value = a6_0;
						element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_REFERENCE), value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_2_0_0_0, a6_0, true);
					copyLocalizationInfos(a6_0, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21453);
			addExpectedElement(null, 21454);
		}
		
		(
			(
				a7_0 = parse_org_emftext_language_java_arrays_ArrayDimension				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
						startIncompleteElement(element);
					}
					if (a7_0 != null) {
						if (a7_0 != null) {
							Object value = a7_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ARRAY_DIMENSIONS_BEFORE, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_2_0_0_1, a7_0, true);
						copyLocalizationInfos(a7_0, element);
					}
				}
			)
			
		)*		{
			// expected elements (follow set)
			addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21455);
			addExpectedElement(null, 21456);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 21457);
	}
	
	(
		a8 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
				startIncompleteElement(element);
			}
			if (a8 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a8.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a8).getLine(), ((CommonToken) a8).getCharPositionInLine(), ((CommonToken) a8).getStartIndex(), ((CommonToken) a8).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_3, resolved, true);
				copyLocalizationInfos((CommonToken) a8, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 21458);
	}
	
	a9 = '(' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a9, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21459, 21506);
		addExpectedElement(null, 21507);
	}
	
	(
		(
			(
				a10_0 = parse_org_emftext_language_java_parameters_Parameter				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
						startIncompleteElement(element);
					}
					if (a10_0 != null) {
						if (a10_0 != null) {
							Object value = a10_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.CLASS_METHOD__PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_5_0_0_0, a10_0, true);
						copyLocalizationInfos(a10_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 21508, 21509);
			}
			
			(
				(
					a11 = ',' {
						if (element == null) {
							element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_5_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a11, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21510, 21557);
					}
					
					(
						a12_0 = parse_org_emftext_language_java_parameters_Parameter						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
								startIncompleteElement(element);
							}
							if (a12_0 != null) {
								if (a12_0 != null) {
									Object value = a12_0;
									addObjectToList(element, org.emftext.language.java.members.MembersPackage.CLASS_METHOD__PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_5_0_0_1_0_0_1, a12_0, true);
								copyLocalizationInfos(a12_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 21558, 21559);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 21560, 21561);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 21562);
	}
	
	a13 = ')' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a13, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21563);
		addExpectedElement(null, 21564, 21565);
	}
	
	(
		(
			a14_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
					startIncompleteElement(element);
				}
				if (a14_0 != null) {
					if (a14_0 != null) {
						Object value = a14_0;
						addObjectToList(element, org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ARRAY_DIMENSIONS_AFTER, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_7, a14_0, true);
					copyLocalizationInfos(a14_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21566);
		addExpectedElement(null, 21567, 21568);
	}
	
	(
		(
			a15 = 'throws' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_8_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a15, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21569, 21570);
			}
			
			(
				a16_0 = parse_org_emftext_language_java_types_NamespaceClassifierReference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
						startIncompleteElement(element);
					}
					if (a16_0 != null) {
						if (a16_0 != null) {
							Object value = a16_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.CLASS_METHOD__EXCEPTIONS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_8_0_0_1, a16_0, true);
						copyLocalizationInfos(a16_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 21571, 21572);
			}
			
			(
				(
					a17 = ',' {
						if (element == null) {
							element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_8_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a17, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21573, 21574);
					}
					
					(
						a18_0 = parse_org_emftext_language_java_types_NamespaceClassifierReference						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
								startIncompleteElement(element);
							}
							if (a18_0 != null) {
								if (a18_0 != null) {
									Object value = a18_0;
									addObjectToList(element, org.emftext.language.java.members.MembersPackage.CLASS_METHOD__EXCEPTIONS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_8_0_0_2_0_0_1, a18_0, true);
								copyLocalizationInfos(a18_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 21575, 21576);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 21577, 21578);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 21579);
	}
	
	a19 = '{' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_10, null, true);
		copyLocalizationInfos((CommonToken)a19, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21580, 21766);
		addExpectedElement(null, 21767);
	}
	
	(
		(
			(
				a20_0 = parse_org_emftext_language_java_statements_Statement				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
						startIncompleteElement(element);
					}
					if (a20_0 != null) {
						if (a20_0 != null) {
							Object value = a20_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.CLASS_METHOD__STATEMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_11_0_0_1, a20_0, true);
						copyLocalizationInfos(a20_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21768, 21954);
				addExpectedElement(null, 21955);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 21956, 22142);
		addExpectedElement(null, 22143);
	}
	
	a21 = '}' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createClassMethod();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_21_0_0_13, null, true);
		copyLocalizationInfos((CommonToken)a21, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 22144, 22321);
		addExpectedElement(null, 22322);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 22323, 22500);
		addExpectedElement(null, 22501);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 22502, 22679);
		addExpectedElement(null, 22680);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 22681, 22858);
		addExpectedElement(null, 22859);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 22860, 23037);
		addExpectedElement(null, 23038);
	}
	
;

parse_org_emftext_language_java_annotations_AnnotationAttribute returns [org.emftext.language.java.annotations.AnnotationAttribute element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23039, 23050);
		addExpectedElement(null, 23051);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23052, 23063);
	}
	
	(
		(
			a1 = '<' {
				if (element == null) {
					element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23064);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_generics_TypeParameter				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 23065, 23066);
			}
			
			(
				(
					a3 = ',' {
						if (element == null) {
							element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_1_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a3, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23067);
					}
					
					(
						a4_0 = parse_org_emftext_language_java_generics_TypeParameter						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
								startIncompleteElement(element);
							}
							if (a4_0 != null) {
								if (a4_0 != null) {
									Object value = a4_0;
									addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_1_0_0_2_0_0_1, a4_0, true);
								copyLocalizationInfos(a4_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 23068, 23069);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 23070, 23071);
			}
			
			a5 = '>' {
				if (element == null) {
					element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_1_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23072, 23083);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23084, 23095);
	}
	
	(
		(
			a6_0 = parse_org_emftext_language_java_types_TypeReference			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
					startIncompleteElement(element);
				}
				if (a6_0 != null) {
					if (a6_0 != null) {
						Object value = a6_0;
						element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_REFERENCE), value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_2_0_0_0, a6_0, true);
					copyLocalizationInfos(a6_0, element);
				}
			}
		)
		{
			// expected elements (follow set)
			addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23096);
			addExpectedElement(null, 23097);
		}
		
		(
			(
				a7_0 = parse_org_emftext_language_java_arrays_ArrayDimension				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
						startIncompleteElement(element);
					}
					if (a7_0 != null) {
						if (a7_0 != null) {
							Object value = a7_0;
							addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ARRAY_DIMENSIONS_BEFORE, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_2_0_0_1, a7_0, true);
						copyLocalizationInfos(a7_0, element);
					}
				}
			)
			
		)*		{
			// expected elements (follow set)
			addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23098);
			addExpectedElement(null, 23099);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 23100);
	}
	
	(
		a8 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
				startIncompleteElement(element);
			}
			if (a8 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a8.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a8).getLine(), ((CommonToken) a8).getCharPositionInLine(), ((CommonToken) a8).getStartIndex(), ((CommonToken) a8).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_3, resolved, true);
				copyLocalizationInfos((CommonToken) a8, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 23101);
	}
	
	a9 = '(' {
		if (element == null) {
			element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a9, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23102, 23149);
		addExpectedElement(null, 23150);
	}
	
	(
		(
			(
				a10_0 = parse_org_emftext_language_java_parameters_Parameter				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
						startIncompleteElement(element);
					}
					if (a10_0 != null) {
						if (a10_0 != null) {
							Object value = a10_0;
							addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__PARAMETERS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_5_0_0_0, a10_0, true);
						copyLocalizationInfos(a10_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 23151, 23152);
			}
			
			(
				(
					a11 = ',' {
						if (element == null) {
							element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_5_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a11, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23153, 23200);
					}
					
					(
						a12_0 = parse_org_emftext_language_java_parameters_Parameter						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
								startIncompleteElement(element);
							}
							if (a12_0 != null) {
								if (a12_0 != null) {
									Object value = a12_0;
									addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__PARAMETERS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_5_0_0_1_0_0_1, a12_0, true);
								copyLocalizationInfos(a12_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 23201, 23202);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 23203, 23204);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 23205);
	}
	
	a13 = ')' {
		if (element == null) {
			element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a13, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23206);
		addExpectedElement(null, 23207, 23208);
	}
	
	(
		(
			a14_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
					startIncompleteElement(element);
				}
				if (a14_0 != null) {
					if (a14_0 != null) {
						Object value = a14_0;
						addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ARRAY_DIMENSIONS_AFTER, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_7, a14_0, true);
					copyLocalizationInfos(a14_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23209);
		addExpectedElement(null, 23210, 23211);
	}
	
	(
		(
			a15 = 'throws' {
				if (element == null) {
					element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_8_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a15, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23212, 23213);
			}
			
			(
				a16_0 = parse_org_emftext_language_java_types_NamespaceClassifierReference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
						startIncompleteElement(element);
					}
					if (a16_0 != null) {
						if (a16_0 != null) {
							Object value = a16_0;
							addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__EXCEPTIONS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_8_0_0_1, a16_0, true);
						copyLocalizationInfos(a16_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 23214, 23215);
			}
			
			(
				(
					a17 = ',' {
						if (element == null) {
							element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_8_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a17, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23216, 23217);
					}
					
					(
						a18_0 = parse_org_emftext_language_java_types_NamespaceClassifierReference						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
								startIncompleteElement(element);
							}
							if (a18_0 != null) {
								if (a18_0 != null) {
									Object value = a18_0;
									addObjectToList(element, org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__EXCEPTIONS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_8_0_0_2_0_0_1, a18_0, true);
								copyLocalizationInfos(a18_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 23218, 23219);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 23220, 23221);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 23222);
	}
	
	a19 = 'default' {
		if (element == null) {
			element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_9, null, true);
		copyLocalizationInfos((CommonToken)a19, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 23223, 23306);
	}
	
	(
		a20_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
				startIncompleteElement(element);
			}
			if (a20_0 != null) {
				if (a20_0 != null) {
					Object value = a20_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__DEFAULT_VALUE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_10, a20_0, true);
				copyLocalizationInfos(a20_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 23307);
	}
	
	a21 = ';' {
		if (element == null) {
			element = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_22_0_0_11, null, true);
		copyLocalizationInfos((CommonToken)a21, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 23308, 23485);
		addExpectedElement(null, 23486);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 23487, 23664);
		addExpectedElement(null, 23665);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 23666, 23843);
		addExpectedElement(null, 23844);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 23845, 24022);
		addExpectedElement(null, 24023);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 24024, 24201);
		addExpectedElement(null, 24202);
	}
	
;

parse_org_emftext_language_java_parameters_OrdinaryParameter returns [org.emftext.language.java.parameters.OrdinaryParameter element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createOrdinaryParameter();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_23_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 24203, 24226);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_types_TypeReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createOrdinaryParameter();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_REFERENCE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_23_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 24227);
		addExpectedElement(null, 24228, 24229);
	}
	
	(
		(
			a2_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createOrdinaryParameter();
					startIncompleteElement(element);
				}
				if (a2_0 != null) {
					if (a2_0 != null) {
						Object value = a2_0;
						addObjectToList(element, org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ARRAY_DIMENSIONS_BEFORE, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_23_0_0_2, a2_0, true);
					copyLocalizationInfos(a2_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 24230);
		addExpectedElement(null, 24231, 24232);
	}
	
	(
		(
			a3 = '<' {
				if (element == null) {
					element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createOrdinaryParameter();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_23_0_0_3_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 24233, 24247);
			}
			
			(
				a4_0 = parse_org_emftext_language_java_generics_TypeArgument				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createOrdinaryParameter();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							addObjectToList(element, org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_23_0_0_3_0_0_1, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 24248, 24249);
			}
			
			(
				(
					a5 = ',' {
						if (element == null) {
							element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createOrdinaryParameter();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_23_0_0_3_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a5, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 24250, 24264);
					}
					
					(
						a6_0 = parse_org_emftext_language_java_generics_TypeArgument						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createOrdinaryParameter();
								startIncompleteElement(element);
							}
							if (a6_0 != null) {
								if (a6_0 != null) {
									Object value = a6_0;
									addObjectToList(element, org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_23_0_0_3_0_0_2_0_0_1, a6_0, true);
								copyLocalizationInfos(a6_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 24265, 24266);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 24267, 24268);
			}
			
			a7 = '>' {
				if (element == null) {
					element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createOrdinaryParameter();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_23_0_0_3_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a7, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 24269);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 24270);
	}
	
	(
		a8 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createOrdinaryParameter();
				startIncompleteElement(element);
			}
			if (a8 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a8.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a8).getLine(), ((CommonToken) a8).getCharPositionInLine(), ((CommonToken) a8).getStartIndex(), ((CommonToken) a8).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_23_0_0_4, resolved, true);
				copyLocalizationInfos((CommonToken) a8, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 24271);
		addExpectedElement(null, 24272, 24281);
	}
	
	(
		(
			a9_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createOrdinaryParameter();
					startIncompleteElement(element);
				}
				if (a9_0 != null) {
					if (a9_0 != null) {
						Object value = a9_0;
						addObjectToList(element, org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ARRAY_DIMENSIONS_AFTER, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_23_0_0_5, a9_0, true);
					copyLocalizationInfos(a9_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 24282);
		addExpectedElement(null, 24283, 24292);
	}
	
;

parse_org_emftext_language_java_parameters_VariableLengthParameter returns [org.emftext.language.java.parameters.VariableLengthParameter element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createVariableLengthParameter();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_24_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 24293, 24316);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_types_TypeReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createVariableLengthParameter();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_REFERENCE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_24_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 24317);
		addExpectedElement(null, 24318, 24319);
	}
	
	(
		(
			a2_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createVariableLengthParameter();
					startIncompleteElement(element);
				}
				if (a2_0 != null) {
					if (a2_0 != null) {
						Object value = a2_0;
						addObjectToList(element, org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__ARRAY_DIMENSIONS_BEFORE, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_24_0_0_2, a2_0, true);
					copyLocalizationInfos(a2_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 24320);
		addExpectedElement(null, 24321, 24322);
	}
	
	(
		(
			a3 = '<' {
				if (element == null) {
					element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createVariableLengthParameter();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_24_0_0_3_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 24323, 24337);
			}
			
			(
				a4_0 = parse_org_emftext_language_java_generics_TypeArgument				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createVariableLengthParameter();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							addObjectToList(element, org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_24_0_0_3_0_0_1, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 24338, 24339);
			}
			
			(
				(
					a5 = ',' {
						if (element == null) {
							element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createVariableLengthParameter();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_24_0_0_3_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a5, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 24340, 24354);
					}
					
					(
						a6_0 = parse_org_emftext_language_java_generics_TypeArgument						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createVariableLengthParameter();
								startIncompleteElement(element);
							}
							if (a6_0 != null) {
								if (a6_0 != null) {
									Object value = a6_0;
									addObjectToList(element, org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_24_0_0_3_0_0_2_0_0_1, a6_0, true);
								copyLocalizationInfos(a6_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 24355, 24356);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 24357, 24358);
			}
			
			a7 = '>' {
				if (element == null) {
					element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createVariableLengthParameter();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_24_0_0_3_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a7, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 24359);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 24360);
	}
	
	a8 = '...' {
		if (element == null) {
			element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createVariableLengthParameter();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_24_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a8, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 24361);
	}
	
	(
		a9 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.parameters.ParametersFactory.eINSTANCE.createVariableLengthParameter();
				startIncompleteElement(element);
			}
			if (a9 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a9.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a9).getLine(), ((CommonToken) a9).getCharPositionInLine(), ((CommonToken) a9).getStartIndex(), ((CommonToken) a9).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_24_0_0_5, resolved, true);
				copyLocalizationInfos((CommonToken) a9, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 24362, 24369);
	}
	
;

parse_org_emftext_language_java_variables_LocalVariable returns [org.emftext.language.java.variables.LocalVariable element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 24370, 24393);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_types_TypeReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_REFERENCE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 24394);
		addExpectedElement(null, 24395, 24396);
	}
	
	(
		(
			a2_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
					startIncompleteElement(element);
				}
				if (a2_0 != null) {
					if (a2_0 != null) {
						Object value = a2_0;
						addObjectToList(element, org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ARRAY_DIMENSIONS_BEFORE, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_2, a2_0, true);
					copyLocalizationInfos(a2_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 24397);
		addExpectedElement(null, 24398, 24399);
	}
	
	(
		(
			a3 = '<' {
				if (element == null) {
					element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_3_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 24400, 24414);
			}
			
			(
				a4_0 = parse_org_emftext_language_java_generics_TypeArgument				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							addObjectToList(element, org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_3_0_0_1, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 24415, 24416);
			}
			
			(
				(
					a5 = ',' {
						if (element == null) {
							element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_3_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a5, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 24417, 24431);
					}
					
					(
						a6_0 = parse_org_emftext_language_java_generics_TypeArgument						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
								startIncompleteElement(element);
							}
							if (a6_0 != null) {
								if (a6_0 != null) {
									Object value = a6_0;
									addObjectToList(element, org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_3_0_0_2_0_0_1, a6_0, true);
								copyLocalizationInfos(a6_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 24432, 24433);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 24434, 24435);
			}
			
			a7 = '>' {
				if (element == null) {
					element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_3_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a7, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 24436);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 24437);
	}
	
	(
		a8 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
				startIncompleteElement(element);
			}
			if (a8 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a8.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a8).getLine(), ((CommonToken) a8).getCharPositionInLine(), ((CommonToken) a8).getStartIndex(), ((CommonToken) a8).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_4, resolved, true);
				copyLocalizationInfos((CommonToken) a8, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 24438);
		addExpectedElement(null, 24439, 24442);
	}
	
	(
		(
			a9_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
					startIncompleteElement(element);
				}
				if (a9_0 != null) {
					if (a9_0 != null) {
						Object value = a9_0;
						addObjectToList(element, org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ARRAY_DIMENSIONS_AFTER, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_5, a9_0, true);
					copyLocalizationInfos(a9_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 24443);
		addExpectedElement(null, 24444, 24447);
	}
	
	(
		(
			a10 = '=' {
				if (element == null) {
					element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_6_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a10, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 24448, 24531);
			}
			
			(
				a11_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
						startIncompleteElement(element);
					}
					if (a11_0 != null) {
						if (a11_0 != null) {
							Object value = a11_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_6_0_0_3, a11_0, true);
						copyLocalizationInfos(a11_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 24532, 24534);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 24535, 24537);
	}
	
	(
		(
			a12 = ',' {
				if (element == null) {
					element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_7_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a12, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 24538);
			}
			
			(
				a13_0 = parse_org_emftext_language_java_variables_AdditionalLocalVariable				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createLocalVariable();
						startIncompleteElement(element);
					}
					if (a13_0 != null) {
						if (a13_0 != null) {
							Object value = a13_0;
							addObjectToList(element, org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_25_0_0_7_0_0_1, a13_0, true);
						copyLocalizationInfos(a13_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 24539, 24541);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 24542, 24544);
	}
	
;

parse_org_emftext_language_java_statements_LocalVariableStatement returns [org.emftext.language.java.statements.LocalVariableStatement element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_variables_LocalVariable		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createLocalVariableStatement();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.LOCAL_VARIABLE_STATEMENT__VARIABLE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_26_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 24545);
	}
	
	a1 = ';' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createLocalVariableStatement();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_26_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 24546, 24732);
		addExpectedElement(null, 24733);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 24734, 24920);
		addExpectedElement(null, 24921);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 24922, 25108);
		addExpectedElement(null, 25109, 25111);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 25112, 25298);
		addExpectedElement(null, 25299);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 25300, 25486);
		addExpectedElement(null, 25487);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 25488, 25674);
		addExpectedElement(null, 25675);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 25676, 25862);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 25863, 25864);
		addExpectedElement(null, 25865);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 25866, 26052);
	}
	
;

parse_org_emftext_language_java_variables_AdditionalLocalVariable returns [org.emftext.language.java.variables.AdditionalLocalVariable element = null]
@init{
}
:
	(
		a0 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createAdditionalLocalVariable();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_27_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable(), 26053);
		addExpectedElement(null, 26054, 26057);
	}
	
	(
		(
			a1_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createAdditionalLocalVariable();
					startIncompleteElement(element);
				}
				if (a1_0 != null) {
					if (a1_0 != null) {
						Object value = a1_0;
						addObjectToList(element, org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__ARRAY_DIMENSIONS_AFTER, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_27_0_0_1, a1_0, true);
					copyLocalizationInfos(a1_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable(), 26058);
		addExpectedElement(null, 26059, 26062);
	}
	
	(
		(
			a2 = '=' {
				if (element == null) {
					element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createAdditionalLocalVariable();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_27_0_0_2_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable(), 26063, 26146);
			}
			
			(
				a3_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.variables.VariablesFactory.eINSTANCE.createAdditionalLocalVariable();
						startIncompleteElement(element);
					}
					if (a3_0 != null) {
						if (a3_0 != null) {
							Object value = a3_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__INITIAL_VALUE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_27_0_0_2_0_0_3, a3_0, true);
						copyLocalizationInfos(a3_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 26147, 26149);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 26150, 26152);
	}
	
;

parse_org_emftext_language_java_members_Field returns [org.emftext.language.java.members.Field element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.members.MembersPackage.FIELD__ANNOTATIONS_AND_MODIFIERS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 26153, 26176);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_types_TypeReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_REFERENCE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 26177);
		addExpectedElement(null, 26178, 26179);
	}
	
	(
		(
			a2_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
					startIncompleteElement(element);
				}
				if (a2_0 != null) {
					if (a2_0 != null) {
						Object value = a2_0;
						addObjectToList(element, org.emftext.language.java.members.MembersPackage.FIELD__ARRAY_DIMENSIONS_BEFORE, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_2, a2_0, true);
					copyLocalizationInfos(a2_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 26180);
		addExpectedElement(null, 26181, 26182);
	}
	
	(
		(
			a3 = '<' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_3_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 26183, 26197);
			}
			
			(
				a4_0 = parse_org_emftext_language_java_generics_TypeArgument				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.FIELD__TYPE_ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_3_0_0_1, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 26198, 26199);
			}
			
			(
				(
					a5 = ',' {
						if (element == null) {
							element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_3_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a5, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 26200, 26214);
					}
					
					(
						a6_0 = parse_org_emftext_language_java_generics_TypeArgument						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
								startIncompleteElement(element);
							}
							if (a6_0 != null) {
								if (a6_0 != null) {
									Object value = a6_0;
									addObjectToList(element, org.emftext.language.java.members.MembersPackage.FIELD__TYPE_ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_3_0_0_2_0_0_1, a6_0, true);
								copyLocalizationInfos(a6_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 26215, 26216);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 26217, 26218);
			}
			
			a7 = '>' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_3_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a7, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 26219);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 26220);
	}
	
	(
		a8 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
				startIncompleteElement(element);
			}
			if (a8 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a8.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a8).getLine(), ((CommonToken) a8).getCharPositionInLine(), ((CommonToken) a8).getStartIndex(), ((CommonToken) a8).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_4, resolved, true);
				copyLocalizationInfos((CommonToken) a8, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 26221);
		addExpectedElement(null, 26222, 26224);
	}
	
	(
		(
			a9_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
					startIncompleteElement(element);
				}
				if (a9_0 != null) {
					if (a9_0 != null) {
						Object value = a9_0;
						addObjectToList(element, org.emftext.language.java.members.MembersPackage.FIELD__ARRAY_DIMENSIONS_AFTER, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_5, a9_0, true);
					copyLocalizationInfos(a9_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 26225);
		addExpectedElement(null, 26226, 26228);
	}
	
	(
		(
			a10 = '=' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_6_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a10, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 26229, 26312);
			}
			
			(
				a11_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
						startIncompleteElement(element);
					}
					if (a11_0 != null) {
						if (a11_0 != null) {
							Object value = a11_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__INITIAL_VALUE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_6_0_0_3, a11_0, true);
						copyLocalizationInfos(a11_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 26313, 26314);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 26315, 26316);
	}
	
	(
		(
			a12 = ',' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_7_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a12, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 26317);
			}
			
			(
				a13_0 = parse_org_emftext_language_java_members_AdditionalField				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
						startIncompleteElement(element);
					}
					if (a13_0 != null) {
						if (a13_0 != null) {
							Object value = a13_0;
							addObjectToList(element, org.emftext.language.java.members.MembersPackage.FIELD__ADDITIONAL_FIELDS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_7_0_0_1, a13_0, true);
						copyLocalizationInfos(a13_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 26318, 26319);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, 26320, 26321);
	}
	
	a14 = ';' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createField();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_28_0_0_8, null, true);
		copyLocalizationInfos((CommonToken)a14, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 26322, 26499);
		addExpectedElement(null, 26500);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 26501, 26678);
		addExpectedElement(null, 26679);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 26680, 26857);
		addExpectedElement(null, 26858);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 26859, 27036);
		addExpectedElement(null, 27037);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 27038, 27215);
		addExpectedElement(null, 27216);
	}
	
;

parse_org_emftext_language_java_members_AdditionalField returns [org.emftext.language.java.members.AdditionalField element = null]
@init{
}
:
	(
		a0 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createAdditionalField();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_29_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField(), 27217);
		addExpectedElement(null, 27218, 27220);
	}
	
	(
		(
			a1_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createAdditionalField();
					startIncompleteElement(element);
				}
				if (a1_0 != null) {
					if (a1_0 != null) {
						Object value = a1_0;
						addObjectToList(element, org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__ARRAY_DIMENSIONS_AFTER, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_29_0_0_1, a1_0, true);
					copyLocalizationInfos(a1_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField(), 27221);
		addExpectedElement(null, 27222, 27224);
	}
	
	(
		(
			a2 = '=' {
				if (element == null) {
					element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createAdditionalField();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_29_0_0_2_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField(), 27225, 27308);
			}
			
			(
				a3_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createAdditionalField();
						startIncompleteElement(element);
					}
					if (a3_0 != null) {
						if (a3_0 != null) {
							Object value = a3_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__INITIAL_VALUE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_29_0_0_2_0_0_3, a3_0, true);
						copyLocalizationInfos(a3_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 27309, 27310);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 27311, 27312);
	}
	
;

parse_org_emftext_language_java_members_EmptyMember returns [org.emftext.language.java.members.EmptyMember element = null]
@init{
}
:
	a0 = ';' {
		if (element == null) {
			element = org.emftext.language.java.members.MembersFactory.eINSTANCE.createEmptyMember();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_30_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 27313, 27490);
		addExpectedElement(null, 27491);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), 27492, 27669);
		addExpectedElement(null, 27670);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 27671, 27848);
		addExpectedElement(null, 27849);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 27850, 28027);
		addExpectedElement(null, 28028);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 28029, 28206);
		addExpectedElement(null, 28207);
	}
	
;

parse_org_emftext_language_java_instantiations_NewConstructorCall returns [org.emftext.language.java.instantiations.NewConstructorCall element = null]
@init{
}
:
	a0 = 'new' {
		if (element == null) {
			element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 28208);
		addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), 28209, 28220);
	}
	
	(
		(
			a1 = '<' {
				if (element == null) {
					element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), 28221, 28235);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_generics_TypeArgument				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 28236, 28237);
			}
			
			(
				(
					a3 = ',' {
						if (element == null) {
							element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_1_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a3, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), 28238, 28252);
					}
					
					(
						a4_0 = parse_org_emftext_language_java_generics_TypeArgument						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
								startIncompleteElement(element);
							}
							if (a4_0 != null) {
								if (a4_0 != null) {
									Object value = a4_0;
									addObjectToList(element, org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_1_0_0_2_0_0_1, a4_0, true);
								copyLocalizationInfos(a4_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 28253, 28254);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 28255, 28256);
			}
			
			a5 = '>' {
				if (element == null) {
					element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_1_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), 28257, 28268);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), 28269, 28280);
	}
	
	(
		a6_0 = parse_org_emftext_language_java_types_TypeReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
				startIncompleteElement(element);
			}
			if (a6_0 != null) {
				if (a6_0 != null) {
					Object value = a6_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_REFERENCE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_2, a6_0, true);
				copyLocalizationInfos(a6_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 28281, 28282);
	}
	
	(
		(
			a7 = '<' {
				if (element == null) {
					element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_3_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a7, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), 28283, 28297);
			}
			
			(
				a8_0 = parse_org_emftext_language_java_generics_TypeArgument				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
						startIncompleteElement(element);
					}
					if (a8_0 != null) {
						if (a8_0 != null) {
							Object value = a8_0;
							addObjectToList(element, org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_3_0_0_1, a8_0, true);
						copyLocalizationInfos(a8_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 28298, 28299);
			}
			
			(
				(
					a9 = ',' {
						if (element == null) {
							element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_3_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a9, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), 28300, 28314);
					}
					
					(
						a10_0 = parse_org_emftext_language_java_generics_TypeArgument						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
								startIncompleteElement(element);
							}
							if (a10_0 != null) {
								if (a10_0 != null) {
									Object value = a10_0;
									addObjectToList(element, org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_3_0_0_2_0_0_1, a10_0, true);
								copyLocalizationInfos(a10_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 28315, 28316);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 28317, 28318);
			}
			
			a11 = '>' {
				if (element == null) {
					element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_3_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a11, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 28319);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 28320);
	}
	
	a12 = '(' {
		if (element == null) {
			element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a12, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), 28321, 28404);
		addExpectedElement(null, 28405);
	}
	
	(
		(
			(
				a13_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
						startIncompleteElement(element);
					}
					if (a13_0 != null) {
						if (a13_0 != null) {
							Object value = a13_0;
							addObjectToList(element, org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_5_0_0_0, a13_0, true);
						copyLocalizationInfos(a13_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 28406, 28407);
			}
			
			(
				(
					a14 = ',' {
						if (element == null) {
							element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_5_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a14, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), 28408, 28491);
					}
					
					(
						a15_0 = parse_org_emftext_language_java_expressions_AssignmentExpression						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
								startIncompleteElement(element);
							}
							if (a15_0 != null) {
								if (a15_0 != null) {
									Object value = a15_0;
									addObjectToList(element, org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_5_0_0_1_0_0_1, a15_0, true);
								copyLocalizationInfos(a15_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 28492, 28493);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 28494, 28495);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 28496);
	}
	
	a16 = ')' {
		if (element == null) {
			element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a16, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), 28497);
		addExpectedElement(null, 28498);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 28499, 28500);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 28501, 28503);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 28504, 28505);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 28506, 28508);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 28509, 28512);
		addExpectedElement(null, 28513);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 28514, 28515);
		addExpectedElement(null, 28516, 28524);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 28525, 28536);
		addExpectedElement(null, 28537, 28562);
	}
	
	(
		(
			a17_0 = parse_org_emftext_language_java_classifiers_AnonymousClass			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
					startIncompleteElement(element);
				}
				if (a17_0 != null) {
					if (a17_0 != null) {
						Object value = a17_0;
						element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS), value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_7, a17_0, true);
					copyLocalizationInfos(a17_0, element);
				}
			}
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 28563);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 28564, 28565);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 28566, 28568);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 28569, 28570);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 28571, 28573);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 28574, 28577);
		addExpectedElement(null, 28578);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 28579, 28580);
		addExpectedElement(null, 28581, 28589);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 28590, 28601);
		addExpectedElement(null, 28602, 28627);
	}
	
	(
		(
			a18 = '.' {
				if (element == null) {
					element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_8_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a18, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), 28628, 28652);
			}
			
			(
				a19_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createNewConstructorCall();
						startIncompleteElement(element);
					}
					if (a19_0 != null) {
						if (a19_0 != null) {
							Object value = a19_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_31_0_0_8_0_0_1, a19_0, true);
						copyLocalizationInfos(a19_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 28653, 28654);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 28655, 28657);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 28658, 28659);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 28660, 28662);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 28663, 28666);
				addExpectedElement(null, 28667);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 28668, 28669);
				addExpectedElement(null, 28670, 28678);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 28679, 28690);
				addExpectedElement(null, 28691, 28716);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 28717, 28718);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 28719, 28721);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 28722, 28723);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 28724, 28726);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 28727, 28730);
		addExpectedElement(null, 28731);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 28732, 28733);
		addExpectedElement(null, 28734, 28742);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 28743, 28754);
		addExpectedElement(null, 28755, 28780);
	}
	
;

parse_org_emftext_language_java_instantiations_ExplicitConstructorCall returns [org.emftext.language.java.instantiations.ExplicitConstructorCall element = null]
@init{
}
:
	(
		(
			a0 = '<' {
				if (element == null) {
					element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_0_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a0, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall(), 28781, 28795);
			}
			
			(
				a1_0 = parse_org_emftext_language_java_generics_TypeArgument				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							addObjectToList(element, org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__TYPE_ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_0_0_0_1, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 28796, 28797);
			}
			
			(
				(
					a2 = ',' {
						if (element == null) {
							element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_0_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a2, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall(), 28798, 28812);
					}
					
					(
						a3_0 = parse_org_emftext_language_java_generics_TypeArgument						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
								startIncompleteElement(element);
							}
							if (a3_0 != null) {
								if (a3_0 != null) {
									Object value = a3_0;
									addObjectToList(element, org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__TYPE_ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_0_0_0_2_0_0_1, a3_0, true);
								copyLocalizationInfos(a3_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 28813, 28814);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 28815, 28816);
			}
			
			a4 = '>' {
				if (element == null) {
					element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_0_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a4, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall(), 28817, 28818);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall(), 28819, 28820);
	}
	
	(
		a5_0 = parse_org_emftext_language_java_literals_Self		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
				startIncompleteElement(element);
			}
			if (a5_0 != null) {
				if (a5_0 != null) {
					Object value = a5_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__CALL_TARGET), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_1, a5_0, true);
				copyLocalizationInfos(a5_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 28821);
	}
	
	a6 = '(' {
		if (element == null) {
			element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall(), 28822, 28905);
		addExpectedElement(null, 28906);
	}
	
	(
		(
			(
				a7_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
						startIncompleteElement(element);
					}
					if (a7_0 != null) {
						if (a7_0 != null) {
							Object value = a7_0;
							addObjectToList(element, org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_3_0_0_0, a7_0, true);
						copyLocalizationInfos(a7_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 28907, 28908);
			}
			
			(
				(
					a8 = ',' {
						if (element == null) {
							element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_3_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a8, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall(), 28909, 28992);
					}
					
					(
						a9_0 = parse_org_emftext_language_java_expressions_AssignmentExpression						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
								startIncompleteElement(element);
							}
							if (a9_0 != null) {
								if (a9_0 != null) {
									Object value = a9_0;
									addObjectToList(element, org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_3_0_0_1_0_0_1, a9_0, true);
								copyLocalizationInfos(a9_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 28993, 28994);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 28995, 28996);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 28997);
	}
	
	a10 = ')' {
		if (element == null) {
			element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a10, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 28998);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 28999, 29000);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29001, 29003);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29004, 29005);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29006, 29008);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29009, 29012);
		addExpectedElement(null, 29013);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29014, 29015);
		addExpectedElement(null, 29016, 29024);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29025, 29036);
		addExpectedElement(null, 29037, 29062);
	}
	
	(
		(
			a11 = '.' {
				if (element == null) {
					element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_5_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a11, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall(), 29063, 29087);
			}
			
			(
				a12_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.instantiations.InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
						startIncompleteElement(element);
					}
					if (a12_0 != null) {
						if (a12_0 != null) {
							Object value = a12_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_32_0_0_5_0_0_1, a12_0, true);
						copyLocalizationInfos(a12_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29088, 29089);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29090, 29092);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29093, 29094);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29095, 29097);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29098, 29101);
				addExpectedElement(null, 29102);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29103, 29104);
				addExpectedElement(null, 29105, 29113);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29114, 29125);
				addExpectedElement(null, 29126, 29151);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29152, 29153);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29154, 29156);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29157, 29158);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29159, 29161);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29162, 29165);
		addExpectedElement(null, 29166);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29167, 29168);
		addExpectedElement(null, 29169, 29177);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29178, 29189);
		addExpectedElement(null, 29190, 29215);
	}
	
;

parse_org_emftext_language_java_arrays_ArrayInstantiationByValuesTyped returns [org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped element = null]
@init{
}
:
	a0 = 'new' {
		if (element == null) {
			element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationByValuesTyped();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_33_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 29216, 29227);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_types_TypeReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationByValuesTyped();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__TYPE_REFERENCE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_33_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 29228);
	}
	
	(
		(
			a2_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationByValuesTyped();
					startIncompleteElement(element);
				}
				if (a2_0 != null) {
					if (a2_0 != null) {
						Object value = a2_0;
						addObjectToList(element, org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_DIMENSIONS_BEFORE, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_33_0_0_2, a2_0, true);
					copyLocalizationInfos(a2_0, element);
				}
			}
		)
		
	)+	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 29229, 29230);
	}
	
	(
		a3_0 = parse_org_emftext_language_java_arrays_ArrayInitializer		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationByValuesTyped();
				startIncompleteElement(element);
			}
			if (a3_0 != null) {
				if (a3_0 != null) {
					Object value = a3_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_INITIALIZER), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_33_0_0_3, a3_0, true);
				copyLocalizationInfos(a3_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 29231);
		addExpectedElement(null, 29232);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29233, 29234);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29235, 29237);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29238, 29239);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29240, 29242);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29243, 29246);
		addExpectedElement(null, 29247);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29248, 29249);
		addExpectedElement(null, 29250, 29258);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29259, 29270);
		addExpectedElement(null, 29271, 29296);
	}
	
	(
		(
			a4_0 = parse_org_emftext_language_java_arrays_ArraySelector			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationByValuesTyped();
					startIncompleteElement(element);
				}
				if (a4_0 != null) {
					if (a4_0 != null) {
						Object value = a4_0;
						addObjectToList(element, org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_SELECTORS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_33_0_0_4, a4_0, true);
					copyLocalizationInfos(a4_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 29297);
		addExpectedElement(null, 29298);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29299, 29300);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29301, 29303);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29304, 29305);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29306, 29308);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29309, 29312);
		addExpectedElement(null, 29313);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29314, 29315);
		addExpectedElement(null, 29316, 29324);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29325, 29336);
		addExpectedElement(null, 29337, 29362);
	}
	
	(
		(
			a5 = '.' {
				if (element == null) {
					element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationByValuesTyped();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_33_0_0_5_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 29363, 29387);
			}
			
			(
				a6_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationByValuesTyped();
						startIncompleteElement(element);
					}
					if (a6_0 != null) {
						if (a6_0 != null) {
							Object value = a6_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_33_0_0_5_0_0_1, a6_0, true);
						copyLocalizationInfos(a6_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29388, 29389);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29390, 29392);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29393, 29394);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29395, 29397);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29398, 29401);
				addExpectedElement(null, 29402);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29403, 29404);
				addExpectedElement(null, 29405, 29413);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29414, 29425);
				addExpectedElement(null, 29426, 29451);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29452, 29453);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29454, 29456);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29457, 29458);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29459, 29461);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29462, 29465);
		addExpectedElement(null, 29466);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29467, 29468);
		addExpectedElement(null, 29469, 29477);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29478, 29489);
		addExpectedElement(null, 29490, 29515);
	}
	
;

parse_org_emftext_language_java_arrays_ArrayInstantiationByValuesUntyped returns [org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_arrays_ArrayInitializer		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationByValuesUntyped();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__ARRAY_INITIALIZER), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_34_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesUntyped(), 29516);
		addExpectedElement(null, 29517);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29518, 29519);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29520, 29522);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29523, 29524);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29525, 29527);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29528, 29531);
		addExpectedElement(null, 29532);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29533, 29534);
		addExpectedElement(null, 29535, 29543);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29544, 29555);
		addExpectedElement(null, 29556, 29581);
	}
	
	(
		(
			a1_0 = parse_org_emftext_language_java_arrays_ArraySelector			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationByValuesUntyped();
					startIncompleteElement(element);
				}
				if (a1_0 != null) {
					if (a1_0 != null) {
						Object value = a1_0;
						addObjectToList(element, org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__ARRAY_SELECTORS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_34_0_0_1, a1_0, true);
					copyLocalizationInfos(a1_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesUntyped(), 29582);
		addExpectedElement(null, 29583);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29584, 29585);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29586, 29588);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29589, 29590);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29591, 29593);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29594, 29597);
		addExpectedElement(null, 29598);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29599, 29600);
		addExpectedElement(null, 29601, 29609);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29610, 29621);
		addExpectedElement(null, 29622, 29647);
	}
	
	(
		(
			a2 = '.' {
				if (element == null) {
					element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationByValuesUntyped();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_34_0_0_2_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesUntyped(), 29648, 29672);
			}
			
			(
				a3_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationByValuesUntyped();
						startIncompleteElement(element);
					}
					if (a3_0 != null) {
						if (a3_0 != null) {
							Object value = a3_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_34_0_0_2_0_0_1, a3_0, true);
						copyLocalizationInfos(a3_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29673, 29674);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29675, 29677);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29678, 29679);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29680, 29682);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29683, 29686);
				addExpectedElement(null, 29687);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29688, 29689);
				addExpectedElement(null, 29690, 29698);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29699, 29710);
				addExpectedElement(null, 29711, 29736);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29737, 29738);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29739, 29741);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29742, 29743);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29744, 29746);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29747, 29750);
		addExpectedElement(null, 29751);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29752, 29753);
		addExpectedElement(null, 29754, 29762);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29763, 29774);
		addExpectedElement(null, 29775, 29800);
	}
	
;

parse_org_emftext_language_java_arrays_ArrayInstantiationBySize returns [org.emftext.language.java.arrays.ArrayInstantiationBySize element = null]
@init{
}
:
	a0 = 'new' {
		if (element == null) {
			element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationBySize();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_35_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize(), 29801, 29812);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_types_TypeReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationBySize();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__TYPE_REFERENCE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_35_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 29813);
	}
	
	(
		(
			a2 = '[' {
				if (element == null) {
					element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationBySize();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_35_0_0_2_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize(), 29814, 29897);
			}
			
			(
				a3_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationBySize();
						startIncompleteElement(element);
					}
					if (a3_0 != null) {
						if (a3_0 != null) {
							Object value = a3_0;
							addObjectToList(element, org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__SIZES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_35_0_0_2_0_0_1, a3_0, true);
						copyLocalizationInfos(a3_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 29898);
			}
			
			a4 = ']' {
				if (element == null) {
					element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationBySize();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_35_0_0_2_0_0_2, null, true);
				copyLocalizationInfos((CommonToken)a4, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 29899);
				addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize(), 29900);
				addExpectedElement(null, 29901);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29902, 29903);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29904, 29906);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29907, 29908);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29909, 29911);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29912, 29915);
				addExpectedElement(null, 29916);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29917, 29918);
				addExpectedElement(null, 29919, 29927);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29928, 29939);
				addExpectedElement(null, 29940, 29965);
			}
			
		)
		
	)+	{
		// expected elements (follow set)
		addExpectedElement(null, 29966);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize(), 29967);
		addExpectedElement(null, 29968);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 29969, 29970);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 29971, 29973);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 29974, 29975);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 29976, 29978);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 29979, 29982);
		addExpectedElement(null, 29983);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 29984, 29985);
		addExpectedElement(null, 29986, 29994);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 29995, 30006);
		addExpectedElement(null, 30007, 30032);
	}
	
	(
		(
			a5_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationBySize();
					startIncompleteElement(element);
				}
				if (a5_0 != null) {
					if (a5_0 != null) {
						Object value = a5_0;
						addObjectToList(element, org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__ARRAY_DIMENSIONS_BEFORE, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_35_0_0_3, a5_0, true);
					copyLocalizationInfos(a5_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize(), 30033);
		addExpectedElement(null, 30034);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 30035, 30036);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 30037, 30039);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 30040, 30041);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 30042, 30044);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 30045, 30048);
		addExpectedElement(null, 30049);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 30050, 30051);
		addExpectedElement(null, 30052, 30060);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 30061, 30072);
		addExpectedElement(null, 30073, 30098);
	}
	
	(
		(
			a6 = '.' {
				if (element == null) {
					element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationBySize();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_35_0_0_4_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a6, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize(), 30099, 30123);
			}
			
			(
				a7_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInstantiationBySize();
						startIncompleteElement(element);
					}
					if (a7_0 != null) {
						if (a7_0 != null) {
							Object value = a7_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_35_0_0_4_0_0_1, a7_0, true);
						copyLocalizationInfos(a7_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 30124, 30125);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 30126, 30128);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 30129, 30130);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 30131, 30133);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 30134, 30137);
				addExpectedElement(null, 30138);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 30139, 30140);
				addExpectedElement(null, 30141, 30149);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 30150, 30161);
				addExpectedElement(null, 30162, 30187);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 30188, 30189);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 30190, 30192);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 30193, 30194);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 30195, 30197);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 30198, 30201);
		addExpectedElement(null, 30202);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 30203, 30204);
		addExpectedElement(null, 30205, 30213);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 30214, 30225);
		addExpectedElement(null, 30226, 30251);
	}
	
;

parse_org_emftext_language_java_arrays_ArrayInitializer returns [org.emftext.language.java.arrays.ArrayInitializer element = null]
@init{
}
:
	a0 = '{' {
		if (element == null) {
			element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInitializer();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_36_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), 30252, 30336);
		addExpectedElement(null, 30337, 30338);
	}
	
	(
		(
			(
				a1_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInitializer();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							addObjectToList(element, org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__INITIAL_VALUES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_36_0_0_2_0_0_0, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
				|				a1_1 = parse_org_emftext_language_java_arrays_ArrayInitializer				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInitializer();
						startIncompleteElement(element);
					}
					if (a1_1 != null) {
						if (a1_1 != null) {
							Object value = a1_1;
							addObjectToList(element, org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__INITIAL_VALUES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_36_0_0_2_0_0_0, a1_1, true);
						copyLocalizationInfos(a1_1, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 30339, 30341);
			}
			
			(
				(
					a2 = ',' {
						if (element == null) {
							element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInitializer();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_36_0_0_2_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a2, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), 30342, 30426);
					}
					
					(
						a3_0 = parse_org_emftext_language_java_expressions_AssignmentExpression						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInitializer();
								startIncompleteElement(element);
							}
							if (a3_0 != null) {
								if (a3_0 != null) {
									Object value = a3_0;
									addObjectToList(element, org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__INITIAL_VALUES, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_36_0_0_2_0_0_1_0_0_1, a3_0, true);
								copyLocalizationInfos(a3_0, element);
							}
						}
						|						a3_1 = parse_org_emftext_language_java_arrays_ArrayInitializer						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInitializer();
								startIncompleteElement(element);
							}
							if (a3_1 != null) {
								if (a3_1 != null) {
									Object value = a3_1;
									addObjectToList(element, org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__INITIAL_VALUES, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_36_0_0_2_0_0_1_0_0_1, a3_1, true);
								copyLocalizationInfos(a3_1, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 30427, 30429);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 30430, 30432);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 30433, 30434);
	}
	
	(
		(
			a4 = ',' {
				if (element == null) {
					element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInitializer();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_36_0_0_3_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a4, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 30435);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 30436);
	}
	
	a5 = '}' {
		if (element == null) {
			element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayInitializer();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_36_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 30437, 30439);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 30440);
		addExpectedElement(null, 30441);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 30442, 30443);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 30444, 30446);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 30447, 30448);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 30449, 30451);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 30452, 30455);
		addExpectedElement(null, 30456);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 30457, 30458);
		addExpectedElement(null, 30459, 30464);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 30465, 30476);
		addExpectedElement(null, 30477, 30502);
	}
	
;

parse_org_emftext_language_java_arrays_ArraySelector returns [org.emftext.language.java.arrays.ArraySelector element = null]
@init{
}
:
	a0 = '[' {
		if (element == null) {
			element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArraySelector();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_37_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), 30503, 30586);
		addExpectedElement(null, 30587);
	}
	
	(
		(
			a1_0 = parse_org_emftext_language_java_expressions_AssignmentExpression			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArraySelector();
					startIncompleteElement(element);
				}
				if (a1_0 != null) {
					if (a1_0 != null) {
						Object value = a1_0;
						element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_SELECTOR__POSITION), value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_37_0_0_1, a1_0, true);
					copyLocalizationInfos(a1_0, element);
				}
			}
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 30588);
	}
	
	a2 = ']' {
		if (element == null) {
			element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArraySelector();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_37_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 30589);
		addExpectedElement(null, 30590);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 30591, 30592);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 30593, 30595);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 30596, 30597);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 30598, 30600);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 30601, 30604);
		addExpectedElement(null, 30605);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 30606, 30607);
		addExpectedElement(null, 30608, 30616);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 30617, 30628);
		addExpectedElement(null, 30629, 30654);
	}
	
;

parse_org_emftext_language_java_types_NamespaceClassifierReference returns [org.emftext.language.java.types.NamespaceClassifierReference element = null]
@init{
}
:
	(
		(
			(
				a0 = IDENTIFIER				
				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createNamespaceClassifierReference();
						startIncompleteElement(element);
					}
					if (a0 != null) {
						org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
						tokenResolver.setOptions(getOptions());
						org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__NAMESPACES), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
						}
						java.lang.String resolved = (java.lang.String) resolvedObject;
						if (resolved != null) {
							Object value = resolved;
							addObjectToList(element, org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__NAMESPACES, value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_38_0_0_0_0_0_0, resolved, true);
						copyLocalizationInfos((CommonToken) a0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 30655);
			}
			
			a1 = '.' {
				if (element == null) {
					element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createNamespaceClassifierReference();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_38_0_0_0_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 30656);
				addExpectedElement(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), 30657);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, 30658);
		addExpectedElement(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), 30659);
	}
	
	(
		(
			(
				a2_0 = parse_org_emftext_language_java_types_ClassifierReference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createNamespaceClassifierReference();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_38_0_0_1_0_0_0, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 30660);
			}
			
			a3 = '.' {
				if (element == null) {
					element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createNamespaceClassifierReference();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_38_0_0_1_0_0_1, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), 30661);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), 30662);
	}
	
	(
		a4_0 = parse_org_emftext_language_java_types_ClassifierReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createNamespaceClassifierReference();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					addObjectToList(element, org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_38_0_0_2, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 30663, 30677);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 30678);
		addExpectedElement(null, 30679, 30680);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 30681);
		addExpectedElement(null, 30682, 30683);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 30684);
		addExpectedElement(null, 30685, 30686);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 30687);
		addExpectedElement(null, 30688, 30691);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 30692);
		addExpectedElement(null, 30693);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 30694);
		addExpectedElement(null, 30695, 30703);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 30704);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 30705);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), 30706);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 30707, 30708);
		addExpectedElement(null, 30709, 30717);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 30718, 30729);
		addExpectedElement(null, 30730, 30750);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression(), 30751);
		addExpectedElement(null, 30752);
	}
	
;

parse_org_emftext_language_java_types_ClassifierReference returns [org.emftext.language.java.types.ClassifierReference element = null]
@init{
}
:
	(
		a0 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createClassifierReference();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TARGET), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.emftext.language.java.classifiers.Classifier proxy = org.emftext.language.java.classifiers.ClassifiersFactory.eINSTANCE.createClass();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.emftext.language.java.resource.java.mopp.JavaContextDependentURIFragmentFactory<org.emftext.language.java.types.ClassifierReference, org.emftext.language.java.classifiers.Classifier>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getClassifierReferenceTargetReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TARGET), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TARGET), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_39_0_0_0, proxy, true);
				copyLocalizationInfos((CommonToken) a0, element);
				copyLocalizationInfos((CommonToken) a0, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 30753, 30768);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 30769);
		addExpectedElement(null, 30770, 30771);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 30772);
		addExpectedElement(null, 30773, 30774);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 30775);
		addExpectedElement(null, 30776, 30777);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 30778);
		addExpectedElement(null, 30779, 30782);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 30783);
		addExpectedElement(null, 30784, 30785);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 30786);
		addExpectedElement(null, 30787, 30795);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 30796);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 30797);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), 30798);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 30799, 30800);
		addExpectedElement(null, 30801, 30809);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 30810, 30821);
		addExpectedElement(null, 30822, 30842);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression(), 30843);
		addExpectedElement(null, 30844);
	}
	
	(
		(
			a1 = '<' {
				if (element == null) {
					element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createClassifierReference();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_39_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference(), 30845, 30859);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_generics_TypeArgument				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createClassifierReference();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TYPE_ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_39_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 30860, 30861);
			}
			
			(
				(
					a3 = ',' {
						if (element == null) {
							element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createClassifierReference();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_39_0_0_1_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a3, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference(), 30862, 30876);
					}
					
					(
						a4_0 = parse_org_emftext_language_java_generics_TypeArgument						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createClassifierReference();
								startIncompleteElement(element);
							}
							if (a4_0 != null) {
								if (a4_0 != null) {
									Object value = a4_0;
									addObjectToList(element, org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TYPE_ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_39_0_0_1_0_0_2_0_0_1, a4_0, true);
								copyLocalizationInfos(a4_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 30877, 30878);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 30879, 30880);
			}
			
			a5 = '>' {
				if (element == null) {
					element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createClassifierReference();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_39_0_0_1_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 30881, 30895);
				addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 30896);
				addExpectedElement(null, 30897, 30898);
				addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 30899);
				addExpectedElement(null, 30900, 30901);
				addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 30902);
				addExpectedElement(null, 30903, 30904);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 30905);
				addExpectedElement(null, 30906, 30909);
				addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 30910);
				addExpectedElement(null, 30911, 30912);
				addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 30913);
				addExpectedElement(null, 30914, 30922);
				addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 30923);
				addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 30924);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), 30925);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 30926, 30927);
				addExpectedElement(null, 30928, 30936);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 30937, 30948);
				addExpectedElement(null, 30949, 30969);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression(), 30970);
				addExpectedElement(null, 30971);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 30972, 30986);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 30987);
		addExpectedElement(null, 30988, 30989);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 30990);
		addExpectedElement(null, 30991, 30992);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 30993);
		addExpectedElement(null, 30994, 30995);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 30996);
		addExpectedElement(null, 30997, 31000);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 31001);
		addExpectedElement(null, 31002, 31003);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 31004);
		addExpectedElement(null, 31005, 31013);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 31014);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 31015);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), 31016);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 31017, 31018);
		addExpectedElement(null, 31019, 31027);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 31028, 31039);
		addExpectedElement(null, 31040, 31060);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression(), 31061);
		addExpectedElement(null, 31062);
	}
	
;

parse_org_emftext_language_java_references_MethodCall returns [org.emftext.language.java.references.MethodCall element = null]
@init{
}
:
	(
		(
			a0 = '<' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_0_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a0, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall(), 31063, 31077);
			}
			
			(
				a1_0 = parse_org_emftext_language_java_generics_TypeArgument				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							addObjectToList(element, org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__CALL_TYPE_ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_0_0_0_1, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 31078, 31079);
			}
			
			(
				(
					a2 = ',' {
						if (element == null) {
							element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_0_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a2, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall(), 31080, 31094);
					}
					
					(
						a3_0 = parse_org_emftext_language_java_generics_TypeArgument						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
								startIncompleteElement(element);
							}
							if (a3_0 != null) {
								if (a3_0 != null) {
									Object value = a3_0;
									addObjectToList(element, org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__CALL_TYPE_ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_0_0_0_2_0_0_1, a3_0, true);
								copyLocalizationInfos(a3_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 31095, 31096);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 31097, 31098);
			}
			
			a4 = '>' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_0_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a4, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 31099);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 31100);
	}
	
	(
		a5 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
				startIncompleteElement(element);
			}
			if (a5 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a5.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TARGET), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a5).getLine(), ((CommonToken) a5).getCharPositionInLine(), ((CommonToken) a5).getStartIndex(), ((CommonToken) a5).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.emftext.language.java.references.ReferenceableElement proxy = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.emftext.language.java.resource.java.mopp.JavaContextDependentURIFragmentFactory<org.emftext.language.java.references.ElementReference, org.emftext.language.java.references.ReferenceableElement>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getElementReferenceTargetReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TARGET), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TARGET), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_1, proxy, true);
				copyLocalizationInfos((CommonToken) a5, element);
				copyLocalizationInfos((CommonToken) a5, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 31101, 31102);
	}
	
	(
		(
			a6 = '<' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_2_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a6, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall(), 31103, 31117);
			}
			
			(
				a7_0 = parse_org_emftext_language_java_generics_TypeArgument				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
						startIncompleteElement(element);
					}
					if (a7_0 != null) {
						if (a7_0 != null) {
							Object value = a7_0;
							addObjectToList(element, org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TYPE_ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_2_0_0_1, a7_0, true);
						copyLocalizationInfos(a7_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 31118, 31119);
			}
			
			(
				(
					a8 = ',' {
						if (element == null) {
							element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_2_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a8, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall(), 31120, 31134);
					}
					
					(
						a9_0 = parse_org_emftext_language_java_generics_TypeArgument						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
								startIncompleteElement(element);
							}
							if (a9_0 != null) {
								if (a9_0 != null) {
									Object value = a9_0;
									addObjectToList(element, org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TYPE_ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_2_0_0_2_0_0_1, a9_0, true);
								copyLocalizationInfos(a9_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 31135, 31136);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 31137, 31138);
			}
			
			a10 = '>' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_2_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a10, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(null, 31139);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 31140);
	}
	
	a11 = '(' {
		if (element == null) {
			element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a11, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall(), 31141, 31224);
		addExpectedElement(null, 31225);
	}
	
	(
		(
			(
				a12_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
						startIncompleteElement(element);
					}
					if (a12_0 != null) {
						if (a12_0 != null) {
							Object value = a12_0;
							addObjectToList(element, org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_4_0_0_0, a12_0, true);
						copyLocalizationInfos(a12_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 31226, 31227);
			}
			
			(
				(
					a13 = ',' {
						if (element == null) {
							element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_4_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a13, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall(), 31228, 31311);
					}
					
					(
						a14_0 = parse_org_emftext_language_java_expressions_AssignmentExpression						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
								startIncompleteElement(element);
							}
							if (a14_0 != null) {
								if (a14_0 != null) {
									Object value = a14_0;
									addObjectToList(element, org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_4_0_0_1_0_0_1, a14_0, true);
								copyLocalizationInfos(a14_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 31312, 31313);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 31314, 31315);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 31316);
	}
	
	a15 = ')' {
		if (element == null) {
			element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_5, null, true);
		copyLocalizationInfos((CommonToken)a15, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall(), 31317);
		addExpectedElement(null, 31318);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 31319, 31320);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 31321, 31323);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 31324, 31325);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 31326, 31328);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 31329, 31332);
		addExpectedElement(null, 31333);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 31334, 31335);
		addExpectedElement(null, 31336, 31344);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 31345, 31356);
		addExpectedElement(null, 31357, 31382);
	}
	
	(
		(
			a16_0 = parse_org_emftext_language_java_arrays_ArraySelector			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
					startIncompleteElement(element);
				}
				if (a16_0 != null) {
					if (a16_0 != null) {
						Object value = a16_0;
						addObjectToList(element, org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARRAY_SELECTORS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_6, a16_0, true);
					copyLocalizationInfos(a16_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall(), 31383);
		addExpectedElement(null, 31384);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 31385, 31386);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 31387, 31389);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 31390, 31391);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 31392, 31394);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 31395, 31398);
		addExpectedElement(null, 31399);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 31400, 31401);
		addExpectedElement(null, 31402, 31410);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 31411, 31422);
		addExpectedElement(null, 31423, 31448);
	}
	
	(
		(
			a17 = '.' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_7_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a17, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall(), 31449, 31473);
			}
			
			(
				a18_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createMethodCall();
						startIncompleteElement(element);
					}
					if (a18_0 != null) {
						if (a18_0 != null) {
							Object value = a18_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_40_0_0_7_0_0_1, a18_0, true);
						copyLocalizationInfos(a18_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 31474, 31475);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 31476, 31478);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 31479, 31480);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 31481, 31483);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 31484, 31487);
				addExpectedElement(null, 31488);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 31489, 31490);
				addExpectedElement(null, 31491, 31499);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 31500, 31511);
				addExpectedElement(null, 31512, 31537);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 31538, 31539);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 31540, 31542);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 31543, 31544);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 31545, 31547);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 31548, 31551);
		addExpectedElement(null, 31552);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 31553, 31554);
		addExpectedElement(null, 31555, 31563);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 31564, 31575);
		addExpectedElement(null, 31576, 31601);
	}
	
;

parse_org_emftext_language_java_references_IdentifierReference returns [org.emftext.language.java.references.IdentifierReference element = null]
@init{
}
:
	(
		a0 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createIdentifierReference();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TARGET), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				String resolved = (String) resolvedObject;
				org.emftext.language.java.references.ReferenceableElement proxy = org.emftext.language.java.annotations.AnnotationsFactory.eINSTANCE.createAnnotationAttribute();
				collectHiddenTokens(element);
				registerContextDependentProxy(new org.emftext.language.java.resource.java.mopp.JavaContextDependentURIFragmentFactory<org.emftext.language.java.references.ElementReference, org.emftext.language.java.references.ReferenceableElement>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getElementReferenceTargetReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TARGET), resolved, proxy);
				if (proxy != null) {
					Object value = proxy;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TARGET), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_41_0_0_0, proxy, true);
				copyLocalizationInfos((CommonToken) a0, element);
				copyLocalizationInfos((CommonToken) a0, proxy);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 31602);
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference(), 31603);
		addExpectedElement(null, 31604);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 31605, 31606);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 31607, 31609);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 31610, 31611);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 31612, 31614);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 31615, 31618);
		addExpectedElement(null, 31619);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 31620, 31621);
		addExpectedElement(null, 31622, 31630);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 31631, 31642);
		addExpectedElement(null, 31643, 31668);
	}
	
	(
		(
			a1 = '<' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createIdentifierReference();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_41_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference(), 31669, 31683);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_generics_TypeArgument				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createIdentifierReference();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TYPE_ARGUMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_41_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 31684, 31685);
			}
			
			(
				(
					a3 = ',' {
						if (element == null) {
							element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createIdentifierReference();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_41_0_0_1_0_0_2_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a3, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference(), 31686, 31700);
					}
					
					(
						a4_0 = parse_org_emftext_language_java_generics_TypeArgument						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createIdentifierReference();
								startIncompleteElement(element);
							}
							if (a4_0 != null) {
								if (a4_0 != null) {
									Object value = a4_0;
									addObjectToList(element, org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TYPE_ARGUMENTS, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_41_0_0_1_0_0_2_0_0_1, a4_0, true);
								copyLocalizationInfos(a4_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 31701, 31702);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 31703, 31704);
			}
			
			a5 = '>' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createIdentifierReference();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_41_0_0_1_0_0_3, null, true);
				copyLocalizationInfos((CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference(), 31705);
				addExpectedElement(null, 31706);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 31707, 31708);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 31709, 31711);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 31712, 31713);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 31714, 31716);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 31717, 31720);
				addExpectedElement(null, 31721);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 31722, 31723);
				addExpectedElement(null, 31724, 31732);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 31733, 31744);
				addExpectedElement(null, 31745, 31770);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference(), 31771);
		addExpectedElement(null, 31772);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 31773, 31774);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 31775, 31777);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 31778, 31779);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 31780, 31782);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 31783, 31786);
		addExpectedElement(null, 31787);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 31788, 31789);
		addExpectedElement(null, 31790, 31798);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 31799, 31810);
		addExpectedElement(null, 31811, 31836);
	}
	
	(
		(
			a6_0 = parse_org_emftext_language_java_arrays_ArraySelector			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createIdentifierReference();
					startIncompleteElement(element);
				}
				if (a6_0 != null) {
					if (a6_0 != null) {
						Object value = a6_0;
						addObjectToList(element, org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__ARRAY_SELECTORS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_41_0_0_2, a6_0, true);
					copyLocalizationInfos(a6_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference(), 31837);
		addExpectedElement(null, 31838);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 31839, 31840);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 31841, 31843);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 31844, 31845);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 31846, 31848);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 31849, 31852);
		addExpectedElement(null, 31853);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 31854, 31855);
		addExpectedElement(null, 31856, 31864);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 31865, 31876);
		addExpectedElement(null, 31877, 31902);
	}
	
	(
		(
			a7 = '.' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createIdentifierReference();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_41_0_0_3_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a7, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference(), 31903, 31927);
			}
			
			(
				a8_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createIdentifierReference();
						startIncompleteElement(element);
					}
					if (a8_0 != null) {
						if (a8_0 != null) {
							Object value = a8_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_41_0_0_3_0_0_1, a8_0, true);
						copyLocalizationInfos(a8_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 31928, 31929);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 31930, 31932);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 31933, 31934);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 31935, 31937);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 31938, 31941);
				addExpectedElement(null, 31942);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 31943, 31944);
				addExpectedElement(null, 31945, 31953);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 31954, 31965);
				addExpectedElement(null, 31966, 31991);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 31992, 31993);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 31994, 31996);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 31997, 31998);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 31999, 32001);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32002, 32005);
		addExpectedElement(null, 32006);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32007, 32008);
		addExpectedElement(null, 32009, 32017);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32018, 32029);
		addExpectedElement(null, 32030, 32055);
	}
	
;

parse_org_emftext_language_java_references_ReflectiveClassReference returns [org.emftext.language.java.references.ReflectiveClassReference element = null]
@init{
}
:
	a0 = 'class' {
		if (element == null) {
			element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createReflectiveClassReference();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_42_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 32056);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32057, 32058);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32059, 32061);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32062, 32063);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32064, 32066);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32067, 32070);
		addExpectedElement(null, 32071);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32072, 32073);
		addExpectedElement(null, 32074, 32082);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32083, 32094);
		addExpectedElement(null, 32095, 32120);
	}
	
	(
		(
			a1 = '.' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createReflectiveClassReference();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_42_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReflectiveClassReference(), 32121, 32145);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createReflectiveClassReference();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.REFLECTIVE_CLASS_REFERENCE__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_42_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32146, 32147);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32148, 32150);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32151, 32152);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32153, 32155);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32156, 32159);
				addExpectedElement(null, 32160);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32161, 32162);
				addExpectedElement(null, 32163, 32171);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32172, 32183);
				addExpectedElement(null, 32184, 32209);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32210, 32211);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32212, 32214);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32215, 32216);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32217, 32219);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32220, 32223);
		addExpectedElement(null, 32224);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32225, 32226);
		addExpectedElement(null, 32227, 32235);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32236, 32247);
		addExpectedElement(null, 32248, 32273);
	}
	
;

parse_org_emftext_language_java_references_SelfReference returns [org.emftext.language.java.references.SelfReference element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_literals_Self		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createSelfReference();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__SELF), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_43_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 32274);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32275, 32276);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32277, 32279);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32280, 32281);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32282, 32284);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32285, 32288);
		addExpectedElement(null, 32289);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32290, 32291);
		addExpectedElement(null, 32292, 32300);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32301, 32312);
		addExpectedElement(null, 32313, 32338);
	}
	
	(
		(
			a1 = '.' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createSelfReference();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_43_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getSelfReference(), 32339, 32363);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createSelfReference();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_43_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32364, 32365);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32366, 32368);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32369, 32370);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32371, 32373);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32374, 32377);
				addExpectedElement(null, 32378);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32379, 32380);
				addExpectedElement(null, 32381, 32389);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32390, 32401);
				addExpectedElement(null, 32402, 32427);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32428, 32429);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32430, 32432);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32433, 32434);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32435, 32437);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32438, 32441);
		addExpectedElement(null, 32442);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32443, 32444);
		addExpectedElement(null, 32445, 32453);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32454, 32465);
		addExpectedElement(null, 32466, 32491);
	}
	
;

parse_org_emftext_language_java_references_PrimitiveTypeReference returns [org.emftext.language.java.references.PrimitiveTypeReference element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_types_PrimitiveType		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createPrimitiveTypeReference();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_44_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 32492);
		addExpectedElement(null, 32493);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32494, 32495);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32496, 32498);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32499, 32500);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32501, 32503);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32504, 32507);
		addExpectedElement(null, 32508);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32509, 32510);
		addExpectedElement(null, 32511, 32519);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32520, 32531);
		addExpectedElement(null, 32532, 32557);
	}
	
	(
		(
			a1_0 = parse_org_emftext_language_java_arrays_ArraySelector			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createPrimitiveTypeReference();
					startIncompleteElement(element);
				}
				if (a1_0 != null) {
					if (a1_0 != null) {
						Object value = a1_0;
						addObjectToList(element, org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__ARRAY_SELECTORS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_44_0_0_1, a1_0, true);
					copyLocalizationInfos(a1_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 32558);
		addExpectedElement(null, 32559);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32560, 32561);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32562, 32564);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32565, 32566);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32567, 32569);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32570, 32573);
		addExpectedElement(null, 32574);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32575, 32576);
		addExpectedElement(null, 32577, 32585);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32586, 32597);
		addExpectedElement(null, 32598, 32623);
	}
	
	(
		(
			a2 = '.' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createPrimitiveTypeReference();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_44_0_0_2_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 32624, 32648);
			}
			
			(
				a3_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createPrimitiveTypeReference();
						startIncompleteElement(element);
					}
					if (a3_0 != null) {
						if (a3_0 != null) {
							Object value = a3_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_44_0_0_2_0_0_1, a3_0, true);
						copyLocalizationInfos(a3_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32649, 32650);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32651, 32653);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32654, 32655);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32656, 32658);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32659, 32662);
				addExpectedElement(null, 32663);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32664, 32665);
				addExpectedElement(null, 32666, 32674);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32675, 32686);
				addExpectedElement(null, 32687, 32712);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32713, 32714);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32715, 32717);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32718, 32719);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32720, 32722);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32723, 32726);
		addExpectedElement(null, 32727);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32728, 32729);
		addExpectedElement(null, 32730, 32738);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32739, 32750);
		addExpectedElement(null, 32751, 32776);
	}
	
;

parse_org_emftext_language_java_literals_This returns [org.emftext.language.java.literals.This element = null]
@init{
}
:
	a0 = 'this' {
		if (element == null) {
			element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createThis();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_45_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 32777, 32778);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32779, 32780);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32781, 32783);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32784, 32785);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32786, 32788);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32789, 32792);
		addExpectedElement(null, 32793);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32794, 32795);
		addExpectedElement(null, 32796, 32804);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32805, 32816);
		addExpectedElement(null, 32817, 32842);
	}
	
;

parse_org_emftext_language_java_literals_Super returns [org.emftext.language.java.literals.Super element = null]
@init{
}
:
	a0 = 'super' {
		if (element == null) {
			element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createSuper();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_46_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 32843, 32844);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32845, 32846);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32847, 32849);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32850, 32851);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32852, 32854);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32855, 32858);
		addExpectedElement(null, 32859);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32860, 32861);
		addExpectedElement(null, 32862, 32870);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32871, 32882);
		addExpectedElement(null, 32883, 32908);
	}
	
;

parse_org_emftext_language_java_references_StringReference returns [org.emftext.language.java.references.StringReference element = null]
@init{
}
:
	(
		a0 = STRING_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createStringReference();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("STRING_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_47_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 32909);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32910, 32911);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 32912, 32914);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 32915, 32916);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 32917, 32919);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 32920, 32923);
		addExpectedElement(null, 32924);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 32925, 32926);
		addExpectedElement(null, 32927, 32935);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 32936, 32947);
		addExpectedElement(null, 32948, 32973);
	}
	
	(
		(
			a1 = '.' {
				if (element == null) {
					element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createStringReference();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_47_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getStringReference(), 32974, 32998);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.references.ReferencesFactory.eINSTANCE.createStringReference();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_47_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 32999, 33000);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 33001, 33003);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 33004, 33005);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 33006, 33008);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 33009, 33012);
				addExpectedElement(null, 33013);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 33014, 33015);
				addExpectedElement(null, 33016, 33024);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 33025, 33036);
				addExpectedElement(null, 33037, 33062);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 33063, 33064);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 33065, 33067);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 33068, 33069);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 33070, 33072);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 33073, 33076);
		addExpectedElement(null, 33077);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 33078, 33079);
		addExpectedElement(null, 33080, 33088);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 33089, 33100);
		addExpectedElement(null, 33101, 33126);
	}
	
;

parse_org_emftext_language_java_generics_QualifiedTypeArgument returns [org.emftext.language.java.generics.QualifiedTypeArgument element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_types_TypeReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createQualifiedTypeArgument();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__TYPE_REFERENCE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_48_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 33127);
		addExpectedElement(null, 33128, 33145);
	}
	
	(
		(
			a1_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createQualifiedTypeArgument();
					startIncompleteElement(element);
				}
				if (a1_0 != null) {
					if (a1_0 != null) {
						Object value = a1_0;
						addObjectToList(element, org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_48_0_0_1, a1_0, true);
					copyLocalizationInfos(a1_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 33146);
		addExpectedElement(null, 33147, 33164);
	}
	
;

parse_org_emftext_language_java_generics_UnknownTypeArgument returns [org.emftext.language.java.generics.UnknownTypeArgument element = null]
@init{
}
:
	a0 = '?' {
		if (element == null) {
			element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createUnknownTypeArgument();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_49_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 33165, 33182);
	}
	
;

parse_org_emftext_language_java_generics_ExtendsTypeArgument returns [org.emftext.language.java.generics.ExtendsTypeArgument element = null]
@init{
}
:
	a0 = '?' {
		if (element == null) {
			element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createExtendsTypeArgument();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_50_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 33183);
	}
	
	a1 = 'extends' {
		if (element == null) {
			element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createExtendsTypeArgument();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_50_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 33184, 33195);
	}
	
	(
		a2_0 = parse_org_emftext_language_java_types_TypeReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createExtendsTypeArgument();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					addObjectToList(element, org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPES, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_50_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 33196);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 33197);
		addExpectedElement(null, 33198, 33215);
	}
	
	(
		(
			a3 = '&' {
				if (element == null) {
					element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createExtendsTypeArgument();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_50_0_0_3_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 33216, 33227);
			}
			
			(
				a4_0 = parse_org_emftext_language_java_types_TypeReference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createExtendsTypeArgument();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							addObjectToList(element, org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_50_0_0_3_0_0_1, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 33228);
				addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 33229);
				addExpectedElement(null, 33230, 33247);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(null, 33248);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 33249);
		addExpectedElement(null, 33250, 33267);
	}
	
	(
		(
			a5_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createExtendsTypeArgument();
					startIncompleteElement(element);
				}
				if (a5_0 != null) {
					if (a5_0 != null) {
						Object value = a5_0;
						addObjectToList(element, org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_50_0_0_4, a5_0, true);
					copyLocalizationInfos(a5_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 33268);
		addExpectedElement(null, 33269, 33286);
	}
	
;

parse_org_emftext_language_java_generics_SuperTypeArgument returns [org.emftext.language.java.generics.SuperTypeArgument element = null]
@init{
}
:
	a0 = '?' {
		if (element == null) {
			element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createSuperTypeArgument();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_51_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 33287);
	}
	
	a1 = 'super' {
		if (element == null) {
			element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createSuperTypeArgument();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_51_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 33288, 33299);
	}
	
	(
		a2_0 = parse_org_emftext_language_java_types_TypeReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createSuperTypeArgument();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__SUPER_TYPE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_51_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 33300);
		addExpectedElement(null, 33301, 33318);
	}
	
	(
		(
			a3_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.generics.GenericsFactory.eINSTANCE.createSuperTypeArgument();
					startIncompleteElement(element);
				}
				if (a3_0 != null) {
					if (a3_0 != null) {
						Object value = a3_0;
						addObjectToList(element, org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_51_0_0_3, a3_0, true);
					copyLocalizationInfos(a3_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 33319);
		addExpectedElement(null, 33320, 33337);
	}
	
;

parse_org_emftext_language_java_statements_Assert returns [org.emftext.language.java.statements.Assert element = null]
@init{
}
:
	a0 = 'assert' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createAssert();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_52_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getAssert(), 33338, 33421);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createAssert();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.ASSERT__CONDITION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_52_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 33422, 33423);
	}
	
	(
		(
			a2 = ':' {
				if (element == null) {
					element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createAssert();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_52_0_0_2_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a2, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getAssert(), 33424, 33507);
			}
			
			(
				a3_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createAssert();
						startIncompleteElement(element);
					}
					if (a3_0 != null) {
						if (a3_0 != null) {
							Object value = a3_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.ASSERT__ERROR_MESSAGE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_52_0_0_2_0_0_1, a3_0, true);
						copyLocalizationInfos(a3_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 33508);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 33509);
	}
	
	a4 = ';' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createAssert();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_52_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 33510, 33696);
		addExpectedElement(null, 33697);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 33698, 33884);
		addExpectedElement(null, 33885);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 33886, 34072);
		addExpectedElement(null, 34073, 34075);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 34076, 34262);
		addExpectedElement(null, 34263);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 34264, 34450);
		addExpectedElement(null, 34451);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 34452, 34638);
		addExpectedElement(null, 34639);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 34640, 34826);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 34827, 34828);
		addExpectedElement(null, 34829);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 34830, 35016);
	}
	
;

parse_org_emftext_language_java_statements_Condition returns [org.emftext.language.java.statements.Condition element = null]
@init{
}
:
	a0 = 'if' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCondition();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_53_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 35017);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCondition();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_53_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCondition(), 35018, 35101);
	}
	
	(
		a2_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCondition();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__CONDITION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_53_0_0_3, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 35102);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCondition();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_53_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCondition(), 35103, 35289);
	}
	
	(
		a4_0 = parse_org_emftext_language_java_statements_Statement		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCondition();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__STATEMENT), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_53_0_0_5, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 35290);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 35291, 35477);
		addExpectedElement(null, 35478);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 35479, 35665);
		addExpectedElement(null, 35666);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 35667, 35853);
		addExpectedElement(null, 35854, 35855);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 35856, 36042);
		addExpectedElement(null, 36043);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 36044, 36230);
		addExpectedElement(null, 36231);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 36232, 36418);
		addExpectedElement(null, 36419);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 36420, 36606);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 36607, 36608);
		addExpectedElement(null, 36609);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 36610, 36796);
	}
	
	(
		(
			a5 = 'else' {
				if (element == null) {
					element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCondition();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_53_0_0_6_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCondition(), 36797, 36983);
			}
			
			(
				a6_0 = parse_org_emftext_language_java_statements_Statement				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCondition();
						startIncompleteElement(element);
					}
					if (a6_0 != null) {
						if (a6_0 != null) {
							Object value = a6_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__ELSE_STATEMENT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_53_0_0_6_0_0_1, a6_0, true);
						copyLocalizationInfos(a6_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 36984, 37170);
				addExpectedElement(null, 37171);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 37172, 37358);
				addExpectedElement(null, 37359);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 37360, 37546);
				addExpectedElement(null, 37547, 37549);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 37550, 37736);
				addExpectedElement(null, 37737);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 37738, 37924);
				addExpectedElement(null, 37925);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 37926, 38112);
				addExpectedElement(null, 38113);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 38114, 38300);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 38301, 38302);
				addExpectedElement(null, 38303);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 38304, 38490);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 38491, 38677);
		addExpectedElement(null, 38678);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 38679, 38865);
		addExpectedElement(null, 38866);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 38867, 39053);
		addExpectedElement(null, 39054, 39056);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 39057, 39243);
		addExpectedElement(null, 39244);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 39245, 39431);
		addExpectedElement(null, 39432);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 39433, 39619);
		addExpectedElement(null, 39620);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 39621, 39807);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 39808, 39809);
		addExpectedElement(null, 39810);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 39811, 39997);
	}
	
;

parse_org_emftext_language_java_statements_ForLoop returns [org.emftext.language.java.statements.ForLoop element = null]
@init{
}
:
	a0 = 'for' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 39998);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop(), 39999, 40106);
		addExpectedElement(null, 40107);
	}
	
	(
		(
			a2_0 = parse_org_emftext_language_java_statements_ForLoopInitializer			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForLoop();
					startIncompleteElement(element);
				}
				if (a2_0 != null) {
					if (a2_0 != null) {
						Object value = a2_0;
						element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__INIT), value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54_0_0_3, a2_0, true);
					copyLocalizationInfos(a2_0, element);
				}
			}
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 40108);
	}
	
	a3 = ';' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop(), 40109, 40192);
		addExpectedElement(null, 40193);
	}
	
	(
		(
			a4_0 = parse_org_emftext_language_java_expressions_AssignmentExpression			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForLoop();
					startIncompleteElement(element);
				}
				if (a4_0 != null) {
					if (a4_0 != null) {
						Object value = a4_0;
						element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__CONDITION), value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54_0_0_5, a4_0, true);
					copyLocalizationInfos(a4_0, element);
				}
			}
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 40194);
	}
	
	a5 = ';' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop(), 40195, 40278);
		addExpectedElement(null, 40279);
	}
	
	(
		(
			(
				a6_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForLoop();
						startIncompleteElement(element);
					}
					if (a6_0 != null) {
						if (a6_0 != null) {
							Object value = a6_0;
							addObjectToList(element, org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__UPDATES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54_0_0_7_0_0_0, a6_0, true);
						copyLocalizationInfos(a6_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 40280, 40281);
			}
			
			(
				(
					a7 = ',' {
						if (element == null) {
							element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForLoop();
							startIncompleteElement(element);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54_0_0_7_0_0_1_0_0_0, null, true);
						copyLocalizationInfos((CommonToken)a7, element);
					}
					{
						// expected elements (follow set)
						addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop(), 40282, 40365);
					}
					
					(
						a8_0 = parse_org_emftext_language_java_expressions_AssignmentExpression						{
							if (terminateParsing) {
								throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
							}
							if (element == null) {
								element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForLoop();
								startIncompleteElement(element);
							}
							if (a8_0 != null) {
								if (a8_0 != null) {
									Object value = a8_0;
									addObjectToList(element, org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__UPDATES, value);
									completedElement(value, true);
								}
								collectHiddenTokens(element);
								retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54_0_0_7_0_0_1_0_0_1, a8_0, true);
								copyLocalizationInfos(a8_0, element);
							}
						}
					)
					{
						// expected elements (follow set)
						addExpectedElement(null, 40366, 40367);
					}
					
				)
				
			)*			{
				// expected elements (follow set)
				addExpectedElement(null, 40368, 40369);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 40370);
	}
	
	a9 = ')' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54_0_0_8, null, true);
		copyLocalizationInfos((CommonToken)a9, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop(), 40371, 40557);
	}
	
	(
		a10_0 = parse_org_emftext_language_java_statements_Statement		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForLoop();
				startIncompleteElement(element);
			}
			if (a10_0 != null) {
				if (a10_0 != null) {
					Object value = a10_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__STATEMENT), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_54_0_0_9, a10_0, true);
				copyLocalizationInfos(a10_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 40558, 40744);
		addExpectedElement(null, 40745);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 40746, 40932);
		addExpectedElement(null, 40933);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 40934, 41120);
		addExpectedElement(null, 41121, 41123);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 41124, 41310);
		addExpectedElement(null, 41311);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 41312, 41498);
		addExpectedElement(null, 41499);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 41500, 41686);
		addExpectedElement(null, 41687);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 41688, 41874);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 41875, 41876);
		addExpectedElement(null, 41877);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 41878, 42064);
	}
	
;

parse_org_emftext_language_java_statements_ForEachLoop returns [org.emftext.language.java.statements.ForEachLoop element = null]
@init{
}
:
	a0 = 'for' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForEachLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_55_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 42065);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForEachLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_55_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForEachLoop(), 42066, 42089);
	}
	
	(
		a2_0 = parse_org_emftext_language_java_parameters_OrdinaryParameter		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForEachLoop();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__NEXT), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_55_0_0_3, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 42090);
	}
	
	a3 = ':' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForEachLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_55_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForEachLoop(), 42091, 42174);
	}
	
	(
		a4_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForEachLoop();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__COLLECTION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_55_0_0_5, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 42175);
	}
	
	a5 = ')' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForEachLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_55_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForEachLoop(), 42176, 42362);
	}
	
	(
		a6_0 = parse_org_emftext_language_java_statements_Statement		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createForEachLoop();
				startIncompleteElement(element);
			}
			if (a6_0 != null) {
				if (a6_0 != null) {
					Object value = a6_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__STATEMENT), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_55_0_0_7, a6_0, true);
				copyLocalizationInfos(a6_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 42363, 42549);
		addExpectedElement(null, 42550);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 42551, 42737);
		addExpectedElement(null, 42738);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 42739, 42925);
		addExpectedElement(null, 42926, 42928);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 42929, 43115);
		addExpectedElement(null, 43116);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 43117, 43303);
		addExpectedElement(null, 43304);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 43305, 43491);
		addExpectedElement(null, 43492);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 43493, 43679);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 43680, 43681);
		addExpectedElement(null, 43682);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 43683, 43869);
	}
	
;

parse_org_emftext_language_java_statements_WhileLoop returns [org.emftext.language.java.statements.WhileLoop element = null]
@init{
}
:
	a0 = 'while' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createWhileLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_56_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 43870);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createWhileLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_56_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getWhileLoop(), 43871, 43954);
	}
	
	(
		a2_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createWhileLoop();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.WHILE_LOOP__CONDITION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_56_0_0_3, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 43955);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createWhileLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_56_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getWhileLoop(), 43956, 44142);
	}
	
	(
		a4_0 = parse_org_emftext_language_java_statements_Statement		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createWhileLoop();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.WHILE_LOOP__STATEMENT), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_56_0_0_5, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 44143, 44329);
		addExpectedElement(null, 44330);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 44331, 44517);
		addExpectedElement(null, 44518);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 44519, 44705);
		addExpectedElement(null, 44706, 44708);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 44709, 44895);
		addExpectedElement(null, 44896);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 44897, 45083);
		addExpectedElement(null, 45084);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 45085, 45271);
		addExpectedElement(null, 45272);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 45273, 45459);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 45460, 45461);
		addExpectedElement(null, 45462);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 45463, 45649);
	}
	
	|//derived choice rules for sub-classes: 
	
	c0 = parse_org_emftext_language_java_statements_DoWhileLoop{ element = c0; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_statements_DoWhileLoop returns [org.emftext.language.java.statements.DoWhileLoop element = null]
@init{
}
:
	a0 = 'do' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createDoWhileLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_57_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDoWhileLoop(), 45650, 45836);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_statements_Statement		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createDoWhileLoop();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DO_WHILE_LOOP__STATEMENT), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_57_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 45837);
	}
	
	a2 = 'while' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createDoWhileLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_57_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 45838);
	}
	
	a3 = '(' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createDoWhileLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_57_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDoWhileLoop(), 45839, 45922);
	}
	
	(
		a4_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createDoWhileLoop();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DO_WHILE_LOOP__CONDITION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_57_0_0_5, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 45923);
	}
	
	a5 = ')' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createDoWhileLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_57_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a5, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 45924);
	}
	
	a6 = ';' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createDoWhileLoop();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_57_0_0_7, null, true);
		copyLocalizationInfos((CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 45925, 46111);
		addExpectedElement(null, 46112);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 46113, 46299);
		addExpectedElement(null, 46300);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 46301, 46487);
		addExpectedElement(null, 46488, 46490);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 46491, 46677);
		addExpectedElement(null, 46678);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 46679, 46865);
		addExpectedElement(null, 46866);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 46867, 47053);
		addExpectedElement(null, 47054);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 47055, 47241);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 47242, 47243);
		addExpectedElement(null, 47244);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 47245, 47431);
	}
	
;

parse_org_emftext_language_java_statements_EmptyStatement returns [org.emftext.language.java.statements.EmptyStatement element = null]
@init{
}
:
	a0 = ';' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createEmptyStatement();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_58_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 47432, 47618);
		addExpectedElement(null, 47619);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 47620, 47806);
		addExpectedElement(null, 47807);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 47808, 47994);
		addExpectedElement(null, 47995, 47997);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 47998, 48184);
		addExpectedElement(null, 48185);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 48186, 48372);
		addExpectedElement(null, 48373);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 48374, 48560);
		addExpectedElement(null, 48561);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 48562, 48748);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 48749, 48750);
		addExpectedElement(null, 48751);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 48752, 48938);
	}
	
;

parse_org_emftext_language_java_statements_SynchronizedBlock returns [org.emftext.language.java.statements.SynchronizedBlock element = null]
@init{
}
:
	a0 = 'synchronized' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSynchronizedBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_59_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 48939);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSynchronizedBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_59_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 48940, 49023);
	}
	
	(
		a2_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSynchronizedBlock();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SYNCHRONIZED_BLOCK__LOCK_PROVIDER), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_59_0_0_3, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 49024);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSynchronizedBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_59_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 49025);
	}
	
	a4 = '{' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSynchronizedBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_59_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 49026, 49212);
		addExpectedElement(null, 49213);
	}
	
	(
		(
			(
				a5_0 = parse_org_emftext_language_java_statements_Statement				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSynchronizedBlock();
						startIncompleteElement(element);
					}
					if (a5_0 != null) {
						if (a5_0 != null) {
							Object value = a5_0;
							addObjectToList(element, org.emftext.language.java.statements.StatementsPackage.SYNCHRONIZED_BLOCK__STATEMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_59_0_0_7_0_0_1, a5_0, true);
						copyLocalizationInfos(a5_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 49214, 49400);
				addExpectedElement(null, 49401);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 49402, 49588);
		addExpectedElement(null, 49589);
	}
	
	a6 = '}' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSynchronizedBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_59_0_0_9, null, true);
		copyLocalizationInfos((CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 49590, 49776);
		addExpectedElement(null, 49777);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 49778, 49964);
		addExpectedElement(null, 49965);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 49966, 50152);
		addExpectedElement(null, 50153, 50155);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 50156, 50342);
		addExpectedElement(null, 50343);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 50344, 50530);
		addExpectedElement(null, 50531);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 50532, 50718);
		addExpectedElement(null, 50719);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 50720, 50906);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 50907, 50908);
		addExpectedElement(null, 50909);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 50910, 51096);
	}
	
;

parse_org_emftext_language_java_statements_TryBlock returns [org.emftext.language.java.statements.TryBlock element = null]
@init{
}
:
	a0 = 'try' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createTryBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_60_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 51097);
	}
	
	a1 = '{' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createTryBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_60_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 51098, 51284);
		addExpectedElement(null, 51285);
	}
	
	(
		(
			(
				a2_0 = parse_org_emftext_language_java_statements_Statement				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createTryBlock();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__STATEMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_60_0_0_3_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 51286, 51472);
				addExpectedElement(null, 51473);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 51474, 51660);
		addExpectedElement(null, 51661);
	}
	
	a3 = '}' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createTryBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_60_0_0_5, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 51662);
		addExpectedElement(null, 51663);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 51664, 51850);
		addExpectedElement(null, 51851);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 51852, 52038);
		addExpectedElement(null, 52039);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 52040, 52226);
		addExpectedElement(null, 52227, 52229);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 52230, 52416);
		addExpectedElement(null, 52417);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 52418, 52604);
		addExpectedElement(null, 52605);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 52606, 52792);
		addExpectedElement(null, 52793);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 52794, 52980);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 52981, 52982);
		addExpectedElement(null, 52983);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 52984, 53170);
	}
	
	(
		(
			a4_0 = parse_org_emftext_language_java_statements_CatchBlock			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createTryBlock();
					startIncompleteElement(element);
				}
				if (a4_0 != null) {
					if (a4_0 != null) {
						Object value = a4_0;
						addObjectToList(element, org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__CATCHE_BLOCKS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_60_0_0_6, a4_0, true);
					copyLocalizationInfos(a4_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 53171);
		addExpectedElement(null, 53172);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 53173, 53359);
		addExpectedElement(null, 53360);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 53361, 53547);
		addExpectedElement(null, 53548);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 53549, 53735);
		addExpectedElement(null, 53736, 53738);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 53739, 53925);
		addExpectedElement(null, 53926);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 53927, 54113);
		addExpectedElement(null, 54114);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 54115, 54301);
		addExpectedElement(null, 54302);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 54303, 54489);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 54490, 54491);
		addExpectedElement(null, 54492);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 54493, 54679);
	}
	
	(
		(
			a5 = 'finally' {
				if (element == null) {
					element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createTryBlock();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_60_0_0_7_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a5, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 54680, 54691);
			}
			
			(
				a6_0 = parse_org_emftext_language_java_statements_Block				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createTryBlock();
						startIncompleteElement(element);
					}
					if (a6_0 != null) {
						if (a6_0 != null) {
							Object value = a6_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__FINALLY_BLOCK), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_60_0_0_7_0_0_1, a6_0, true);
						copyLocalizationInfos(a6_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 54692, 54878);
				addExpectedElement(null, 54879);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 54880, 55066);
				addExpectedElement(null, 55067);
				addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 55068, 55254);
				addExpectedElement(null, 55255, 55257);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 55258, 55444);
				addExpectedElement(null, 55445);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 55446, 55632);
				addExpectedElement(null, 55633);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 55634, 55820);
				addExpectedElement(null, 55821);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 55822, 56008);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 56009, 56010);
				addExpectedElement(null, 56011);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 56012, 56198);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 56199, 56385);
		addExpectedElement(null, 56386);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 56387, 56573);
		addExpectedElement(null, 56574);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 56575, 56761);
		addExpectedElement(null, 56762, 56764);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 56765, 56951);
		addExpectedElement(null, 56952);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 56953, 57139);
		addExpectedElement(null, 57140);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 57141, 57327);
		addExpectedElement(null, 57328);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 57329, 57515);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 57516, 57517);
		addExpectedElement(null, 57518);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 57519, 57705);
	}
	
;

parse_org_emftext_language_java_statements_CatchBlock returns [org.emftext.language.java.statements.CatchBlock element = null]
@init{
}
:
	a0 = 'catch' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCatchBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_61_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 57706);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCatchBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_61_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 57707, 57730);
	}
	
	(
		a2_0 = parse_org_emftext_language_java_parameters_OrdinaryParameter		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCatchBlock();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CATCH_BLOCK__PARAMETER), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_61_0_0_3, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 57731);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCatchBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_61_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 57732);
	}
	
	a4 = '{' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCatchBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_61_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 57733, 57919);
		addExpectedElement(null, 57920);
	}
	
	(
		(
			(
				a5_0 = parse_org_emftext_language_java_statements_Statement				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCatchBlock();
						startIncompleteElement(element);
					}
					if (a5_0 != null) {
						if (a5_0 != null) {
							Object value = a5_0;
							addObjectToList(element, org.emftext.language.java.statements.StatementsPackage.CATCH_BLOCK__STATEMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_61_0_0_7_0_0_1, a5_0, true);
						copyLocalizationInfos(a5_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 57921, 58107);
				addExpectedElement(null, 58108);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 58109, 58295);
		addExpectedElement(null, 58296);
	}
	
	a6 = '}' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createCatchBlock();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_61_0_0_9, null, true);
		copyLocalizationInfos((CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 58297);
		addExpectedElement(null, 58298);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 58299, 58485);
		addExpectedElement(null, 58486);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 58487, 58673);
		addExpectedElement(null, 58674);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 58675, 58861);
		addExpectedElement(null, 58862, 58864);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 58865, 59051);
		addExpectedElement(null, 59052);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 59053, 59239);
		addExpectedElement(null, 59240);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 59241, 59427);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 59428, 59429);
		addExpectedElement(null, 59430);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 59431, 59617);
	}
	
;

parse_org_emftext_language_java_statements_Switch returns [org.emftext.language.java.statements.Switch element = null]
@init{
}
:
	a0 = 'switch' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSwitch();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_62_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 59618);
	}
	
	a1 = '(' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSwitch();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_62_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 59619, 59702);
	}
	
	(
		a2_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSwitch();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SWITCH__VARIABLE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_62_0_0_3, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 59703);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSwitch();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_62_0_0_4, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 59704);
	}
	
	a4 = '{' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSwitch();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_62_0_0_6, null, true);
		copyLocalizationInfos((CommonToken)a4, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 59705, 59706);
		addExpectedElement(null, 59707);
	}
	
	(
		(
			(
				a5_0 = parse_org_emftext_language_java_statements_SwitchCase				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSwitch();
						startIncompleteElement(element);
					}
					if (a5_0 != null) {
						if (a5_0 != null) {
							Object value = a5_0;
							addObjectToList(element, org.emftext.language.java.statements.StatementsPackage.SWITCH__CASES, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_62_0_0_7_0_0_0, a5_0, true);
						copyLocalizationInfos(a5_0, element);
					}
				}
			)
			
		)*		{
			// expected elements (follow set)
			addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 59708, 59709);
			addExpectedElement(null, 59710);
		}
		
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 59711);
	}
	
	a6 = '}' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createSwitch();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_62_0_0_8, null, true);
		copyLocalizationInfos((CommonToken)a6, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 59712, 59898);
		addExpectedElement(null, 59899);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 59900, 60086);
		addExpectedElement(null, 60087);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 60088, 60274);
		addExpectedElement(null, 60275, 60277);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 60278, 60464);
		addExpectedElement(null, 60465);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 60466, 60652);
		addExpectedElement(null, 60653);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 60654, 60840);
		addExpectedElement(null, 60841);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 60842, 61028);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 61029, 61030);
		addExpectedElement(null, 61031);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 61032, 61218);
	}
	
;

parse_org_emftext_language_java_statements_NormalSwitchCase returns [org.emftext.language.java.statements.NormalSwitchCase element = null]
@init{
}
:
	a0 = 'case' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createNormalSwitchCase();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_63_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 61219, 61302);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createNormalSwitchCase();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.NORMAL_SWITCH_CASE__CONDITION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_63_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 61303);
	}
	
	a2 = ':' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createNormalSwitchCase();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_63_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 61304, 61490);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 61491, 61492);
		addExpectedElement(null, 61493);
	}
	
	(
		(
			(
				a3_0 = parse_org_emftext_language_java_statements_Statement				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createNormalSwitchCase();
						startIncompleteElement(element);
					}
					if (a3_0 != null) {
						if (a3_0 != null) {
							Object value = a3_0;
							addObjectToList(element, org.emftext.language.java.statements.StatementsPackage.NORMAL_SWITCH_CASE__STATEMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_63_0_0_3_0_0_1, a3_0, true);
						copyLocalizationInfos(a3_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 61494, 61680);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 61681, 61682);
				addExpectedElement(null, 61683);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 61684, 61870);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 61871, 61872);
		addExpectedElement(null, 61873);
	}
	
;

parse_org_emftext_language_java_statements_DefaultSwitchCase returns [org.emftext.language.java.statements.DefaultSwitchCase element = null]
@init{
}
:
	a0 = 'default' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createDefaultSwitchCase();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_64_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 61874);
	}
	
	a1 = ':' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createDefaultSwitchCase();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_64_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 61875, 62061);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 62062, 62063);
		addExpectedElement(null, 62064);
	}
	
	(
		(
			(
				a2_0 = parse_org_emftext_language_java_statements_Statement				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createDefaultSwitchCase();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.statements.StatementsPackage.DEFAULT_SWITCH_CASE__STATEMENTS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_64_0_0_2_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 62065, 62251);
				addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 62252, 62253);
				addExpectedElement(null, 62254);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 62255, 62441);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 62442, 62443);
		addExpectedElement(null, 62444);
	}
	
;

parse_org_emftext_language_java_statements_Return returns [org.emftext.language.java.statements.Return element = null]
@init{
}
:
	a0 = 'return' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createReturn();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_65_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getReturn(), 62445, 62528);
		addExpectedElement(null, 62529);
	}
	
	(
		(
			a1_0 = parse_org_emftext_language_java_expressions_AssignmentExpression			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createReturn();
					startIncompleteElement(element);
				}
				if (a1_0 != null) {
					if (a1_0 != null) {
						Object value = a1_0;
						element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.RETURN__RETURN_VALUE), value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_65_0_0_1, a1_0, true);
					copyLocalizationInfos(a1_0, element);
				}
			}
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 62530);
	}
	
	a2 = ';' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createReturn();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_65_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 62531, 62717);
		addExpectedElement(null, 62718);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 62719, 62905);
		addExpectedElement(null, 62906);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 62907, 63093);
		addExpectedElement(null, 63094, 63096);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 63097, 63283);
		addExpectedElement(null, 63284);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 63285, 63471);
		addExpectedElement(null, 63472);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 63473, 63659);
		addExpectedElement(null, 63660);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 63661, 63847);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 63848, 63849);
		addExpectedElement(null, 63850);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 63851, 64037);
	}
	
;

parse_org_emftext_language_java_statements_Throw returns [org.emftext.language.java.statements.Throw element = null]
@init{
}
:
	a0 = 'throw' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createThrow();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_66_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getThrow(), 64038, 64121);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createThrow();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.THROW__THROWABLE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_66_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 64122);
	}
	
	a2 = ';' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createThrow();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_66_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 64123, 64309);
		addExpectedElement(null, 64310);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 64311, 64497);
		addExpectedElement(null, 64498);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 64499, 64685);
		addExpectedElement(null, 64686, 64688);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 64689, 64875);
		addExpectedElement(null, 64876);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 64877, 65063);
		addExpectedElement(null, 65064);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 65065, 65251);
		addExpectedElement(null, 65252);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 65253, 65439);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 65440, 65441);
		addExpectedElement(null, 65442);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 65443, 65629);
	}
	
;

parse_org_emftext_language_java_statements_Break returns [org.emftext.language.java.statements.Break element = null]
@init{
}
:
	a0 = 'break' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createBreak();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_67_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 65630, 65631);
	}
	
	(
		(
			(
				a1 = IDENTIFIER				
				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createBreak();
						startIncompleteElement(element);
					}
					if (a1 != null) {
						org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
						tokenResolver.setOptions(getOptions());
						org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BREAK__TARGET), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
						}
						String resolved = (String) resolvedObject;
						org.emftext.language.java.statements.JumpLabel proxy = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createJumpLabel();
						collectHiddenTokens(element);
						registerContextDependentProxy(new org.emftext.language.java.resource.java.mopp.JavaContextDependentURIFragmentFactory<org.emftext.language.java.statements.Jump, org.emftext.language.java.statements.JumpLabel>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getJumpTargetReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BREAK__TARGET), resolved, proxy);
						if (proxy != null) {
							Object value = proxy;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BREAK__TARGET), value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_67_0_0_1_0_0_0, proxy, true);
						copyLocalizationInfos((CommonToken) a1, element);
						copyLocalizationInfos((CommonToken) a1, proxy);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 65632);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 65633);
	}
	
	a2 = ';' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createBreak();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_67_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 65634, 65820);
		addExpectedElement(null, 65821);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 65822, 66008);
		addExpectedElement(null, 66009);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 66010, 66196);
		addExpectedElement(null, 66197, 66199);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 66200, 66386);
		addExpectedElement(null, 66387);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 66388, 66574);
		addExpectedElement(null, 66575);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 66576, 66762);
		addExpectedElement(null, 66763);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 66764, 66950);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 66951, 66952);
		addExpectedElement(null, 66953);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 66954, 67140);
	}
	
;

parse_org_emftext_language_java_statements_Continue returns [org.emftext.language.java.statements.Continue element = null]
@init{
}
:
	a0 = 'continue' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createContinue();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_68_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 67141, 67142);
	}
	
	(
		(
			(
				a1 = IDENTIFIER				
				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createContinue();
						startIncompleteElement(element);
					}
					if (a1 != null) {
						org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
						tokenResolver.setOptions(getOptions());
						org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
						tokenResolver.resolve(a1.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONTINUE__TARGET), result);
						Object resolvedObject = result.getResolvedToken();
						if (resolvedObject == null) {
							addErrorToResource(result.getErrorMessage(), ((CommonToken) a1).getLine(), ((CommonToken) a1).getCharPositionInLine(), ((CommonToken) a1).getStartIndex(), ((CommonToken) a1).getStopIndex());
						}
						String resolved = (String) resolvedObject;
						org.emftext.language.java.statements.JumpLabel proxy = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createJumpLabel();
						collectHiddenTokens(element);
						registerContextDependentProxy(new org.emftext.language.java.resource.java.mopp.JavaContextDependentURIFragmentFactory<org.emftext.language.java.statements.Jump, org.emftext.language.java.statements.JumpLabel>(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getJumpTargetReferenceResolver()), element, (EReference) element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONTINUE__TARGET), resolved, proxy);
						if (proxy != null) {
							Object value = proxy;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONTINUE__TARGET), value);
							completedElement(value, false);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_68_0_0_1_0_0_0, proxy, true);
						copyLocalizationInfos((CommonToken) a1, element);
						copyLocalizationInfos((CommonToken) a1, proxy);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 67143);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(null, 67144);
	}
	
	a2 = ';' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createContinue();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_68_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 67145, 67331);
		addExpectedElement(null, 67332);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 67333, 67519);
		addExpectedElement(null, 67520);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 67521, 67707);
		addExpectedElement(null, 67708, 67710);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 67711, 67897);
		addExpectedElement(null, 67898);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 67899, 68085);
		addExpectedElement(null, 68086);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 68087, 68273);
		addExpectedElement(null, 68274);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 68275, 68461);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 68462, 68463);
		addExpectedElement(null, 68464);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 68465, 68651);
	}
	
;

parse_org_emftext_language_java_statements_JumpLabel returns [org.emftext.language.java.statements.JumpLabel element = null]
@init{
}
:
	(
		a0 = IDENTIFIER		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createJumpLabel();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("IDENTIFIER");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__NAME), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.String resolved = (java.lang.String) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__NAME), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_69_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 68652);
	}
	
	a1 = ':' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createJumpLabel();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_69_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getJumpLabel(), 68653, 68839);
	}
	
	(
		a2_0 = parse_org_emftext_language_java_statements_Statement		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createJumpLabel();
				startIncompleteElement(element);
			}
			if (a2_0 != null) {
				if (a2_0 != null) {
					Object value = a2_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__STATEMENT), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_69_0_0_2, a2_0, true);
				copyLocalizationInfos(a2_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 68840, 69026);
		addExpectedElement(null, 69027);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 69028, 69214);
		addExpectedElement(null, 69215);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 69216, 69402);
		addExpectedElement(null, 69403, 69405);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 69406, 69592);
		addExpectedElement(null, 69593);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 69594, 69780);
		addExpectedElement(null, 69781);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 69782, 69968);
		addExpectedElement(null, 69969);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 69970, 70156);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 70157, 70158);
		addExpectedElement(null, 70159);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 70160, 70346);
	}
	
;

parse_org_emftext_language_java_statements_ExpressionStatement returns [org.emftext.language.java.statements.ExpressionStatement element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createExpressionStatement();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.EXPRESSION_STATEMENT__EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_70_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 70347);
	}
	
	a1 = ';' {
		if (element == null) {
			element = org.emftext.language.java.statements.StatementsFactory.eINSTANCE.createExpressionStatement();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_70_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 70348, 70534);
		addExpectedElement(null, 70535);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 70536, 70722);
		addExpectedElement(null, 70723);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 70724, 70910);
		addExpectedElement(null, 70911, 70913);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), 70914, 71100);
		addExpectedElement(null, 71101);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), 71102, 71288);
		addExpectedElement(null, 71289);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), 71290, 71476);
		addExpectedElement(null, 71477);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), 71478, 71664);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), 71665, 71666);
		addExpectedElement(null, 71667);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), 71668, 71854);
	}
	
;

parse_org_emftext_language_java_expressions_ExpressionList returns [org.emftext.language.java.expressions.ExpressionList element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createExpressionList();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.EXPRESSION_LIST__EXPRESSIONS, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_71_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 71855, 71856);
	}
	
	(
		(
			a1 = ',' {
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createExpressionList();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_71_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExpressionList(), 71857, 71940);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createExpressionList();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.EXPRESSION_LIST__EXPRESSIONS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_71_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 71941, 71942);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 71943, 71944);
	}
	
;

parse_org_emftext_language_java_expressions_AssignmentExpression returns [org.emftext.language.java.expressions.AssignmentExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_ConditionalExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createAssignmentExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__CHILD), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_72_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 71945, 71956);
		addExpectedElement(null, 71957, 71990);
	}
	
	(
		(
			(
				a1_0 = parse_org_emftext_language_java_operators_AssignmentOperator				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createAssignmentExpression();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__ASSIGNMENT_OPERATOR), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_72_0_0_1_0_0_1, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 71991, 72074);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createAssignmentExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__VALUE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_72_0_0_1_0_0_3, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 72075, 72108);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 72109, 72142);
	}
	
;

parse_org_emftext_language_java_expressions_ConditionalExpression returns [org.emftext.language.java.expressions.ConditionalExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_ConditionalOrExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createConditionalExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__CHILD), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_73_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 72143, 72146);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 72147, 72158);
		addExpectedElement(null, 72159, 72192);
	}
	
	(
		(
			a1 = '?' {
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createConditionalExpression();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_73_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), 72193, 72276);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_AssignmentExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createConditionalExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__EXPRESSION_IF), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_73_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(null, 72277);
			}
			
			a3 = ':' {
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createConditionalExpression();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_73_0_0_1_0_0_2, null, true);
				copyLocalizationInfos((CommonToken)a3, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), 72278, 72361);
			}
			
			(
				a4_0 = parse_org_emftext_language_java_expressions_ConditionalExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createConditionalExpression();
						startIncompleteElement(element);
					}
					if (a4_0 != null) {
						if (a4_0 != null) {
							Object value = a4_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__EXPRESSION_ELSE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_73_0_0_1_0_0_3, a4_0, true);
						copyLocalizationInfos(a4_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 72362, 72364);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 72365, 72376);
				addExpectedElement(null, 72377, 72410);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 72411, 72413);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 72414, 72425);
		addExpectedElement(null, 72426, 72459);
	}
	
;

parse_org_emftext_language_java_expressions_ConditionalOrExpression returns [org.emftext.language.java.expressions.ConditionalOrExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_ConditionalAndExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createConditionalOrExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_OR_EXPRESSION__CHILDREN, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_74_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 72460, 72464);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 72465, 72476);
		addExpectedElement(null, 72477, 72509);
	}
	
	(
		(
			a1 = '||' {
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createConditionalOrExpression();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_74_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalOrExpression(), 72510, 72593);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_ConditionalAndExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createConditionalOrExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_OR_EXPRESSION__CHILDREN, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_74_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 72594, 72598);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 72599, 72610);
				addExpectedElement(null, 72611, 72643);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 72644, 72648);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 72649, 72660);
		addExpectedElement(null, 72661, 72693);
	}
	
;

parse_org_emftext_language_java_expressions_ConditionalAndExpression returns [org.emftext.language.java.expressions.ConditionalAndExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_InclusiveOrExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createConditionalAndExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_AND_EXPRESSION__CHILDREN, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_75_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 72694, 72699);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 72700, 72711);
		addExpectedElement(null, 72712, 72744);
	}
	
	(
		(
			a1 = '&&' {
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createConditionalAndExpression();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_75_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalAndExpression(), 72745, 72828);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_InclusiveOrExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createConditionalAndExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_AND_EXPRESSION__CHILDREN, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_75_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 72829, 72834);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 72835, 72846);
				addExpectedElement(null, 72847, 72879);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 72880, 72885);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 72886, 72897);
		addExpectedElement(null, 72898, 72930);
	}
	
;

parse_org_emftext_language_java_expressions_InclusiveOrExpression returns [org.emftext.language.java.expressions.InclusiveOrExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_ExclusiveOrExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createInclusiveOrExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.INCLUSIVE_OR_EXPRESSION__CHILDREN, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_76_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 72931, 72937);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 72938, 72949);
		addExpectedElement(null, 72950, 72982);
	}
	
	(
		(
			a1 = '|' {
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createInclusiveOrExpression();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_76_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInclusiveOrExpression(), 72983, 73066);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_ExclusiveOrExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createInclusiveOrExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.INCLUSIVE_OR_EXPRESSION__CHILDREN, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_76_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 73067, 73073);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73074, 73085);
				addExpectedElement(null, 73086, 73118);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 73119, 73125);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73126, 73137);
		addExpectedElement(null, 73138, 73170);
	}
	
;

parse_org_emftext_language_java_expressions_ExclusiveOrExpression returns [org.emftext.language.java.expressions.ExclusiveOrExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_AndExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createExclusiveOrExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.EXCLUSIVE_OR_EXPRESSION__CHILDREN, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_77_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 73171, 73178);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73179, 73190);
		addExpectedElement(null, 73191, 73223);
	}
	
	(
		(
			a1 = '^' {
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createExclusiveOrExpression();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_77_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExclusiveOrExpression(), 73224, 73307);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_AndExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createExclusiveOrExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.EXCLUSIVE_OR_EXPRESSION__CHILDREN, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_77_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 73308, 73315);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73316, 73327);
				addExpectedElement(null, 73328, 73360);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 73361, 73368);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73369, 73380);
		addExpectedElement(null, 73381, 73413);
	}
	
;

parse_org_emftext_language_java_expressions_AndExpression returns [org.emftext.language.java.expressions.AndExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_EqualityExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createAndExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.AND_EXPRESSION__CHILDREN, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_78_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 73414, 73422);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73423, 73434);
		addExpectedElement(null, 73435, 73467);
	}
	
	(
		(
			a1 = '&' {
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createAndExpression();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_78_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAndExpression(), 73468, 73551);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_EqualityExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createAndExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.AND_EXPRESSION__CHILDREN, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_78_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(null, 73552, 73560);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73561, 73572);
				addExpectedElement(null, 73573, 73605);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 73606, 73614);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73615, 73626);
		addExpectedElement(null, 73627, 73659);
	}
	
;

parse_org_emftext_language_java_expressions_EqualityExpression returns [org.emftext.language.java.expressions.EqualityExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_InstanceOfExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createEqualityExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__CHILDREN, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_79_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 73660, 73661);
		addExpectedElement(null, 73662, 73670);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73671, 73682);
		addExpectedElement(null, 73683, 73715);
	}
	
	(
		(
			(
				a1_0 = parse_org_emftext_language_java_operators_EqualityOperator				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createEqualityExpression();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__EQUALITY_OPERATORS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_79_0_0_1_0_0_1, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 73716, 73799);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_InstanceOfExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createEqualityExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__CHILDREN, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_79_0_0_1_0_0_3, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 73800, 73801);
				addExpectedElement(null, 73802, 73810);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73811, 73822);
				addExpectedElement(null, 73823, 73855);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 73856, 73857);
		addExpectedElement(null, 73858, 73866);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73867, 73878);
		addExpectedElement(null, 73879, 73911);
	}
	
;

parse_org_emftext_language_java_expressions_InstanceOfExpression returns [org.emftext.language.java.expressions.InstanceOfExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_RelationExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createInstanceOfExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__CHILD), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_80_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 73912);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 73913, 73914);
		addExpectedElement(null, 73915, 73923);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73924, 73935);
		addExpectedElement(null, 73936, 73968);
	}
	
	(
		(
			a1 = 'instanceof' {
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createInstanceOfExpression();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_80_0_0_1_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a1, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), 73969, 73980);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_types_TypeReference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createInstanceOfExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__TYPE_REFERENCE), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_80_0_0_1_0_0_1, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), 73981);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 73982, 73983);
				addExpectedElement(null, 73984, 73992);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 73993, 74004);
				addExpectedElement(null, 74005, 74037);
			}
			
			(
				(
					a3_0 = parse_org_emftext_language_java_arrays_ArrayDimension					{
						if (terminateParsing) {
							throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
						}
						if (element == null) {
							element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createInstanceOfExpression();
							startIncompleteElement(element);
						}
						if (a3_0 != null) {
							if (a3_0 != null) {
								Object value = a3_0;
								addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__ARRAY_DIMENSIONS_BEFORE, value);
								completedElement(value, true);
							}
							collectHiddenTokens(element);
							retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_80_0_0_1_0_0_2, a3_0, true);
							copyLocalizationInfos(a3_0, element);
						}
					}
				)
				
			)*			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), 74038);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74039, 74040);
				addExpectedElement(null, 74041, 74049);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 74050, 74061);
				addExpectedElement(null, 74062, 74094);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74095, 74096);
		addExpectedElement(null, 74097, 74105);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 74106, 74117);
		addExpectedElement(null, 74118, 74150);
	}
	
;

parse_org_emftext_language_java_expressions_RelationExpression returns [org.emftext.language.java.expressions.RelationExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_ShiftExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createRelationExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__CHILDREN, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_81_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 74151, 74154);
		addExpectedElement(null, 74155);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74156, 74157);
		addExpectedElement(null, 74158, 74166);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 74167, 74178);
		addExpectedElement(null, 74179, 74211);
	}
	
	(
		(
			(
				a1_0 = parse_org_emftext_language_java_operators_RelationOperator				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createRelationExpression();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__RELATION_OPERATORS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_81_0_0_1_0_0_1, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 74212, 74295);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_ShiftExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createRelationExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__CHILDREN, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_81_0_0_1_0_0_3, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 74296, 74299);
				addExpectedElement(null, 74300);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74301, 74302);
				addExpectedElement(null, 74303, 74311);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 74312, 74323);
				addExpectedElement(null, 74324, 74356);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 74357, 74360);
		addExpectedElement(null, 74361);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74362, 74363);
		addExpectedElement(null, 74364, 74372);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 74373, 74384);
		addExpectedElement(null, 74385, 74417);
	}
	
;

parse_org_emftext_language_java_expressions_ShiftExpression returns [org.emftext.language.java.expressions.ShiftExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_AdditiveExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createShiftExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__CHILDREN, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_82_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 74418, 74420);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 74421, 74424);
		addExpectedElement(null, 74425);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74426, 74427);
		addExpectedElement(null, 74428, 74436);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 74437, 74448);
		addExpectedElement(null, 74449, 74481);
	}
	
	(
		(
			(
				a1_0 = parse_org_emftext_language_java_operators_ShiftOperator				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createShiftExpression();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__SHIFT_OPERATORS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_82_0_0_1_0_0_1, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 74482, 74565);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_AdditiveExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createShiftExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__CHILDREN, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_82_0_0_1_0_0_3, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 74566, 74568);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 74569, 74572);
				addExpectedElement(null, 74573);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74574, 74575);
				addExpectedElement(null, 74576, 74584);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 74585, 74596);
				addExpectedElement(null, 74597, 74629);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 74630, 74632);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 74633, 74636);
		addExpectedElement(null, 74637);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74638, 74639);
		addExpectedElement(null, 74640, 74648);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 74649, 74660);
		addExpectedElement(null, 74661, 74693);
	}
	
;

parse_org_emftext_language_java_expressions_AdditiveExpression returns [org.emftext.language.java.expressions.AdditiveExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_MultiplicativeExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createAdditiveExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__CHILDREN, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_83_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 74694, 74695);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 74696, 74698);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 74699, 74702);
		addExpectedElement(null, 74703);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74704, 74705);
		addExpectedElement(null, 74706, 74714);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 74715, 74726);
		addExpectedElement(null, 74727, 74759);
	}
	
	(
		(
			(
				a1_0 = parse_org_emftext_language_java_operators_AdditiveOperator				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createAdditiveExpression();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_83_0_0_1_0_0_1, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 74760, 74843);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_MultiplicativeExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createAdditiveExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__CHILDREN, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_83_0_0_1_0_0_3, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 74844, 74845);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 74846, 74848);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 74849, 74852);
				addExpectedElement(null, 74853);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74854, 74855);
				addExpectedElement(null, 74856, 74864);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 74865, 74876);
				addExpectedElement(null, 74877, 74909);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 74910, 74911);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 74912, 74914);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 74915, 74918);
		addExpectedElement(null, 74919);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74920, 74921);
		addExpectedElement(null, 74922, 74930);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 74931, 74942);
		addExpectedElement(null, 74943, 74975);
	}
	
;

parse_org_emftext_language_java_expressions_MultiplicativeExpression returns [org.emftext.language.java.expressions.MultiplicativeExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_UnaryExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createMultiplicativeExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__CHILDREN, value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_84_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 74976, 74978);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 74979, 74980);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 74981, 74983);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 74984, 74987);
		addExpectedElement(null, 74988);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 74989, 74990);
		addExpectedElement(null, 74991, 74999);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 75000, 75011);
		addExpectedElement(null, 75012, 75044);
	}
	
	(
		(
			(
				a1_0 = parse_org_emftext_language_java_operators_MultiplicativeOperator				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createMultiplicativeExpression();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_84_0_0_1_0_0_1, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 75045, 75128);
			}
			
			(
				a2_0 = parse_org_emftext_language_java_expressions_UnaryExpression				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createMultiplicativeExpression();
						startIncompleteElement(element);
					}
					if (a2_0 != null) {
						if (a2_0 != null) {
							Object value = a2_0;
							addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__CHILDREN, value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_84_0_0_1_0_0_3, a2_0, true);
						copyLocalizationInfos(a2_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 75129, 75131);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 75132, 75133);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 75134, 75136);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 75137, 75140);
				addExpectedElement(null, 75141);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 75142, 75143);
				addExpectedElement(null, 75144, 75152);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 75153, 75164);
				addExpectedElement(null, 75165, 75197);
			}
			
		)
		
	)*	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 75198, 75200);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 75201, 75202);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 75203, 75205);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 75206, 75209);
		addExpectedElement(null, 75210);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 75211, 75212);
		addExpectedElement(null, 75213, 75221);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 75222, 75233);
		addExpectedElement(null, 75234, 75266);
	}
	
;

parse_org_emftext_language_java_expressions_UnaryExpression returns [org.emftext.language.java.expressions.UnaryExpression element = null]
@init{
}
:
	(
		(
			a0_0 = parse_org_emftext_language_java_operators_UnaryOperator			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createUnaryExpression();
					startIncompleteElement(element);
				}
				if (a0_0 != null) {
					if (a0_0 != null) {
						Object value = a0_0;
						addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.UNARY_EXPRESSION__OPERATORS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_85_0_0_0, a0_0, true);
					copyLocalizationInfos(a0_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), 75267, 75350);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_expressions_UnaryModificationExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createUnaryExpression();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.UNARY_EXPRESSION__CHILD), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_85_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 75351, 75353);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 75354, 75355);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 75356, 75358);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 75359, 75362);
		addExpectedElement(null, 75363);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 75364, 75365);
		addExpectedElement(null, 75366, 75374);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 75375, 75386);
		addExpectedElement(null, 75387, 75419);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 75420, 75421);
	}
	
;

parse_org_emftext_language_java_expressions_SuffixUnaryModificationExpression returns [org.emftext.language.java.expressions.SuffixUnaryModificationExpression element = null]
@init{
}
:
	(
		a0_0 = parse_org_emftext_language_java_expressions_UnaryModificationExpressionChild		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createSuffixUnaryModificationExpression();
				startIncompleteElement(element);
			}
			if (a0_0 != null) {
				if (a0_0 != null) {
					Object value = a0_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SUFFIX_UNARY_MODIFICATION_EXPRESSION__CHILD), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_86_0_0_0, a0_0, true);
				copyLocalizationInfos(a0_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 75422, 75423);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 75424, 75426);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 75427, 75428);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 75429, 75431);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 75432, 75435);
		addExpectedElement(null, 75436);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 75437, 75438);
		addExpectedElement(null, 75439, 75447);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 75448, 75459);
		addExpectedElement(null, 75460, 75492);
	}
	
	(
		(
			(
				a1_0 = parse_org_emftext_language_java_operators_UnaryModificationOperator				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createSuffixUnaryModificationExpression();
						startIncompleteElement(element);
					}
					if (a1_0 != null) {
						if (a1_0 != null) {
							Object value = a1_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SUFFIX_UNARY_MODIFICATION_EXPRESSION__OPERATOR), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_86_0_0_1_0_0_0, a1_0, true);
						copyLocalizationInfos(a1_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 75493, 75495);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 75496, 75497);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 75498, 75500);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 75501, 75504);
				addExpectedElement(null, 75505);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 75506, 75507);
				addExpectedElement(null, 75508, 75516);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 75517, 75528);
				addExpectedElement(null, 75529, 75561);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 75562, 75563);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 75564, 75566);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 75567, 75568);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 75569, 75571);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 75572, 75575);
		addExpectedElement(null, 75576);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 75577, 75578);
		addExpectedElement(null, 75579, 75587);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 75588, 75599);
		addExpectedElement(null, 75600, 75632);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 75633, 75634);
	}
	
;

parse_org_emftext_language_java_expressions_PrefixUnaryModificationExpression returns [org.emftext.language.java.expressions.PrefixUnaryModificationExpression element = null]
@init{
}
:
	(
		(
			(
				a0_0 = parse_org_emftext_language_java_operators_UnaryModificationOperator				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createPrefixUnaryModificationExpression();
						startIncompleteElement(element);
					}
					if (a0_0 != null) {
						if (a0_0 != null) {
							Object value = a0_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.PREFIX_UNARY_MODIFICATION_EXPRESSION__OPERATOR), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_87_0_0_0_0_0_0, a0_0, true);
						copyLocalizationInfos(a0_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getPrefixUnaryModificationExpression(), 75635, 75673);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getPrefixUnaryModificationExpression(), 75674, 75712);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_expressions_UnaryModificationExpressionChild		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createPrefixUnaryModificationExpression();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.PREFIX_UNARY_MODIFICATION_EXPRESSION__CHILD), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_87_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 75713, 75715);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 75716, 75717);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 75718, 75720);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 75721, 75724);
		addExpectedElement(null, 75725);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 75726, 75727);
		addExpectedElement(null, 75728, 75736);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 75737, 75748);
		addExpectedElement(null, 75749, 75781);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 75782, 75783);
	}
	
;

parse_org_emftext_language_java_expressions_CastExpression returns [org.emftext.language.java.expressions.CastExpression element = null]
@init{
}
:
	a0 = '(' {
		if (element == null) {
			element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createCastExpression();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_88_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression(), 75784, 75795);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_types_TypeReference		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createCastExpression();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__TYPE_REFERENCE), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_88_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression(), 75796);
		addExpectedElement(null, 75797);
	}
	
	(
		(
			a2_0 = parse_org_emftext_language_java_arrays_ArrayDimension			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createCastExpression();
					startIncompleteElement(element);
				}
				if (a2_0 != null) {
					if (a2_0 != null) {
						Object value = a2_0;
						addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_88_0_0_2, a2_0, true);
					copyLocalizationInfos(a2_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression(), 75798);
		addExpectedElement(null, 75799);
	}
	
	a3 = ')' {
		if (element == null) {
			element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createCastExpression();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_88_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression(), 75800, 75883);
	}
	
	(
		a4_0 = parse_org_emftext_language_java_expressions_UnaryExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createCastExpression();
				startIncompleteElement(element);
			}
			if (a4_0 != null) {
				if (a4_0 != null) {
					Object value = a4_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__CHILD), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_88_0_0_5, a4_0, true);
				copyLocalizationInfos(a4_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 75884, 75885);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 75886, 75888);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 75889, 75890);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 75891, 75893);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 75894, 75897);
		addExpectedElement(null, 75898);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 75899, 75900);
		addExpectedElement(null, 75901, 75909);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 75910, 75921);
		addExpectedElement(null, 75922, 75954);
	}
	
;

parse_org_emftext_language_java_expressions_NestedExpression returns [org.emftext.language.java.expressions.NestedExpression element = null]
@init{
}
:
	a0 = '(' {
		if (element == null) {
			element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createNestedExpression();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_89_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression(), 75955, 76038);
	}
	
	(
		a1_0 = parse_org_emftext_language_java_expressions_AssignmentExpression		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createNestedExpression();
				startIncompleteElement(element);
			}
			if (a1_0 != null) {
				if (a1_0 != null) {
					Object value = a1_0;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__EXPRESSION), value);
					completedElement(value, true);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_89_0_0_1, a1_0, true);
				copyLocalizationInfos(a1_0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		addExpectedElement(null, 76039);
	}
	
	a2 = ')' {
		if (element == null) {
			element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createNestedExpression();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_89_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression(), 76040);
		addExpectedElement(null, 76041);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 76042, 76043);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 76044, 76046);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 76047, 76048);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 76049, 76051);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 76052, 76055);
		addExpectedElement(null, 76056);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 76057, 76058);
		addExpectedElement(null, 76059, 76067);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76068, 76079);
		addExpectedElement(null, 76080, 76105);
	}
	
	(
		(
			a3_0 = parse_org_emftext_language_java_arrays_ArraySelector			{
				if (terminateParsing) {
					throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
				}
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createNestedExpression();
					startIncompleteElement(element);
				}
				if (a3_0 != null) {
					if (a3_0 != null) {
						Object value = a3_0;
						addObjectToList(element, org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__ARRAY_SELECTORS, value);
						completedElement(value, true);
					}
					collectHiddenTokens(element);
					retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_89_0_0_3, a3_0, true);
					copyLocalizationInfos(a3_0, element);
				}
			}
		)
		
	)*	{
		// expected elements (follow set)
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression(), 76106);
		addExpectedElement(null, 76107);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 76108, 76109);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 76110, 76112);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 76113, 76114);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 76115, 76117);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 76118, 76121);
		addExpectedElement(null, 76122);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 76123, 76124);
		addExpectedElement(null, 76125, 76133);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76134, 76145);
		addExpectedElement(null, 76146, 76171);
	}
	
	(
		(
			a4 = '.' {
				if (element == null) {
					element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createNestedExpression();
					startIncompleteElement(element);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_89_0_0_4_0_0_0, null, true);
				copyLocalizationInfos((CommonToken)a4, element);
			}
			{
				// expected elements (follow set)
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression(), 76172, 76196);
			}
			
			(
				a5_0 = parse_org_emftext_language_java_references_Reference				{
					if (terminateParsing) {
						throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
					}
					if (element == null) {
						element = org.emftext.language.java.expressions.ExpressionsFactory.eINSTANCE.createNestedExpression();
						startIncompleteElement(element);
					}
					if (a5_0 != null) {
						if (a5_0 != null) {
							Object value = a5_0;
							element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__NEXT), value);
							completedElement(value, true);
						}
						collectHiddenTokens(element);
						retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_89_0_0_4_0_0_1, a5_0, true);
						copyLocalizationInfos(a5_0, element);
					}
				}
			)
			{
				// expected elements (follow set)
				// We've found the last token for this rule. The constructed EObject is now
				// complete.
				completedElement(element, true);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 76197, 76198);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 76199, 76201);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 76202, 76203);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 76204, 76206);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 76207, 76210);
				addExpectedElement(null, 76211);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 76212, 76213);
				addExpectedElement(null, 76214, 76222);
				addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76223, 76234);
				addExpectedElement(null, 76235, 76260);
			}
			
		)
		
	)?	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 76261, 76262);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 76263, 76265);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 76266, 76267);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 76268, 76270);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 76271, 76274);
		addExpectedElement(null, 76275);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 76276, 76277);
		addExpectedElement(null, 76278, 76286);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76287, 76298);
		addExpectedElement(null, 76299, 76324);
	}
	
;

parse_org_emftext_language_java_operators_Assignment returns [org.emftext.language.java.operators.Assignment element = null]
@init{
}
:
	a0 = '=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignment();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_90_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76325, 76408);
	}
	
;

parse_org_emftext_language_java_operators_AssignmentPlus returns [org.emftext.language.java.operators.AssignmentPlus element = null]
@init{
}
:
	a0 = '+=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentPlus();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_91_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76409, 76492);
	}
	
;

parse_org_emftext_language_java_operators_AssignmentMinus returns [org.emftext.language.java.operators.AssignmentMinus element = null]
@init{
}
:
	a0 = '-=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentMinus();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_92_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76493, 76576);
	}
	
;

parse_org_emftext_language_java_operators_AssignmentMultiplication returns [org.emftext.language.java.operators.AssignmentMultiplication element = null]
@init{
}
:
	a0 = '*=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentMultiplication();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_93_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76577, 76660);
	}
	
;

parse_org_emftext_language_java_operators_AssignmentDivision returns [org.emftext.language.java.operators.AssignmentDivision element = null]
@init{
}
:
	a0 = '/=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentDivision();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_94_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76661, 76744);
	}
	
;

parse_org_emftext_language_java_operators_AssignmentAnd returns [org.emftext.language.java.operators.AssignmentAnd element = null]
@init{
}
:
	a0 = '&=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentAnd();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_95_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76745, 76828);
	}
	
;

parse_org_emftext_language_java_operators_AssignmentOr returns [org.emftext.language.java.operators.AssignmentOr element = null]
@init{
}
:
	a0 = '|=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentOr();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_96_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76829, 76912);
	}
	
;

parse_org_emftext_language_java_operators_AssignmentExclusiveOr returns [org.emftext.language.java.operators.AssignmentExclusiveOr element = null]
@init{
}
:
	a0 = '^=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentExclusiveOr();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_97_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76913, 76996);
	}
	
;

parse_org_emftext_language_java_operators_AssignmentModulo returns [org.emftext.language.java.operators.AssignmentModulo element = null]
@init{
}
:
	a0 = '\u0025=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentModulo();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_98_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 76997, 77080);
	}
	
;

parse_org_emftext_language_java_operators_AssignmentLeftShift returns [org.emftext.language.java.operators.AssignmentLeftShift element = null]
@init{
}
:
	a0 = '<' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentLeftShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_99_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 77081);
	}
	
	a1 = '<' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentLeftShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_99_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 77082);
	}
	
	a2 = '=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentLeftShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_99_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 77083, 77166);
	}
	
;

parse_org_emftext_language_java_operators_AssignmentRightShift returns [org.emftext.language.java.operators.AssignmentRightShift element = null]
@init{
}
:
	a0 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_100_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 77167);
	}
	
	a1 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_100_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 77168);
	}
	
	a2 = '=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_100_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 77169, 77252);
	}
	
;

parse_org_emftext_language_java_operators_AssignmentUnsignedRightShift returns [org.emftext.language.java.operators.AssignmentUnsignedRightShift element = null]
@init{
}
:
	a0 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentUnsignedRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_101_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 77253);
	}
	
	a1 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentUnsignedRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_101_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 77254);
	}
	
	a2 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentUnsignedRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_101_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 77255);
	}
	
	a3 = '=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAssignmentUnsignedRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_101_0_0_3, null, true);
		copyLocalizationInfos((CommonToken)a3, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 77256, 77339);
	}
	
;

parse_org_emftext_language_java_operators_Addition returns [org.emftext.language.java.operators.Addition element = null]
@init{
}
:
	a0 = '+' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createAddition();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_102_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 77340, 77423);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), 77424, 77507);
	}
	
;

parse_org_emftext_language_java_operators_Subtraction returns [org.emftext.language.java.operators.Subtraction element = null]
@init{
}
:
	a0 = '-' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createSubtraction();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_103_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 77508, 77591);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), 77592, 77675);
	}
	
;

parse_org_emftext_language_java_operators_Multiplication returns [org.emftext.language.java.operators.Multiplication element = null]
@init{
}
:
	a0 = '*' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createMultiplication();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_104_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 77676, 77759);
	}
	
;

parse_org_emftext_language_java_operators_Division returns [org.emftext.language.java.operators.Division element = null]
@init{
}
:
	a0 = '/' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createDivision();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_105_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 77760, 77843);
	}
	
;

parse_org_emftext_language_java_operators_Remainder returns [org.emftext.language.java.operators.Remainder element = null]
@init{
}
:
	a0 = '\u0025' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createRemainder();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_106_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 77844, 77927);
	}
	
;

parse_org_emftext_language_java_operators_LessThan returns [org.emftext.language.java.operators.LessThan element = null]
@init{
}
:
	a0 = '<' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createLessThan();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_107_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 77928, 78011);
	}
	
;

parse_org_emftext_language_java_operators_LessThanOrEqual returns [org.emftext.language.java.operators.LessThanOrEqual element = null]
@init{
}
:
	a0 = '<' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createLessThanOrEqual();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_108_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 78012);
	}
	
	a1 = '=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createLessThanOrEqual();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_108_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 78013, 78096);
	}
	
;

parse_org_emftext_language_java_operators_GreaterThan returns [org.emftext.language.java.operators.GreaterThan element = null]
@init{
}
:
	a0 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createGreaterThan();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_109_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 78097, 78180);
	}
	
;

parse_org_emftext_language_java_operators_GreaterThanOrEqual returns [org.emftext.language.java.operators.GreaterThanOrEqual element = null]
@init{
}
:
	a0 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createGreaterThanOrEqual();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_110_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 78181);
	}
	
	a1 = '=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createGreaterThanOrEqual();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_110_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 78182, 78265);
	}
	
;

parse_org_emftext_language_java_operators_LeftShift returns [org.emftext.language.java.operators.LeftShift element = null]
@init{
}
:
	a0 = '<' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createLeftShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_111_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 78266);
	}
	
	a1 = '<' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createLeftShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_111_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 78267, 78350);
	}
	
;

parse_org_emftext_language_java_operators_RightShift returns [org.emftext.language.java.operators.RightShift element = null]
@init{
}
:
	a0 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_112_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 78351);
	}
	
	a1 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_112_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 78352, 78435);
	}
	
;

parse_org_emftext_language_java_operators_UnsignedRightShift returns [org.emftext.language.java.operators.UnsignedRightShift element = null]
@init{
}
:
	a0 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createUnsignedRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_113_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 78436);
	}
	
	a1 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createUnsignedRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_113_0_0_1, null, true);
		copyLocalizationInfos((CommonToken)a1, element);
	}
	{
		// expected elements (follow set)
		addExpectedElement(null, 78437);
	}
	
	a2 = '>' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createUnsignedRightShift();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_113_0_0_2, null, true);
		copyLocalizationInfos((CommonToken)a2, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 78438, 78521);
	}
	
;

parse_org_emftext_language_java_operators_Equal returns [org.emftext.language.java.operators.Equal element = null]
@init{
}
:
	a0 = '==' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createEqual();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_114_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 78522, 78605);
	}
	
;

parse_org_emftext_language_java_operators_NotEqual returns [org.emftext.language.java.operators.NotEqual element = null]
@init{
}
:
	a0 = '!=' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createNotEqual();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_115_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 78606, 78689);
	}
	
;

parse_org_emftext_language_java_operators_PlusPlus returns [org.emftext.language.java.operators.PlusPlus element = null]
@init{
}
:
	a0 = '++' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createPlusPlus();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_116_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 78690, 78692);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 78693, 78694);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 78695, 78697);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 78698, 78701);
		addExpectedElement(null, 78702);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 78703, 78704);
		addExpectedElement(null, 78705, 78713);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 78714, 78725);
		addExpectedElement(null, 78726, 78758);
	}
	
;

parse_org_emftext_language_java_operators_MinusMinus returns [org.emftext.language.java.operators.MinusMinus element = null]
@init{
}
:
	a0 = '--' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createMinusMinus();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_117_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 78759, 78761);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 78762, 78763);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 78764, 78766);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 78767, 78770);
		addExpectedElement(null, 78771);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 78772, 78773);
		addExpectedElement(null, 78774, 78782);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 78783, 78794);
		addExpectedElement(null, 78795, 78827);
	}
	
;

parse_org_emftext_language_java_operators_Complement returns [org.emftext.language.java.operators.Complement element = null]
@init{
}
:
	a0 = '~' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createComplement();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_118_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), 78828, 78911);
	}
	
;

parse_org_emftext_language_java_operators_Negate returns [org.emftext.language.java.operators.Negate element = null]
@init{
}
:
	a0 = '!' {
		if (element == null) {
			element = org.emftext.language.java.operators.OperatorsFactory.eINSTANCE.createNegate();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_119_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), 78912, 78995);
	}
	
;

parse_org_emftext_language_java_arrays_ArrayDimension returns [org.emftext.language.java.arrays.ArrayDimension element = null]
@init{
}
:
	(
		a0 = '[' {
			if (element == null) {
				element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayDimension();
				startIncompleteElement(element);
			}
			collectHiddenTokens(element);
			retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_120_0_0_0_0_0_0, null, true);
			copyLocalizationInfos((CommonToken)a0, element);
		}
		{
			// expected elements (follow set)
			addExpectedElement(null, 78996);
		}
		
		a1 = ']' {
			if (element == null) {
				element = org.emftext.language.java.arrays.ArraysFactory.eINSTANCE.createArrayDimension();
				startIncompleteElement(element);
			}
			collectHiddenTokens(element);
			retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_120_0_0_0_0_0_1, null, true);
			copyLocalizationInfos((CommonToken)a1, element);
		}
		{
			// expected elements (follow set)
			// We've found the last token for this rule. The constructed EObject is now
			// complete.
			completedElement(element, true);
			addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 78997);
			addExpectedElement(null, 78998);
			addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 78999);
			addExpectedElement(null, 79000);
			addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 79001);
			addExpectedElement(null, 79002);
			addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 79003);
			addExpectedElement(null, 79004, 79005);
			addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 79006);
			addExpectedElement(null, 79007, 79008);
			addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 79009);
			addExpectedElement(null, 79010, 79011);
			addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable(), 79012);
			addExpectedElement(null, 79013);
			addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 79014);
			addExpectedElement(null, 79015, 79016);
			addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField(), 79017);
			addExpectedElement(null, 79018);
			addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 79019, 79020);
			addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize(), 79021);
			addExpectedElement(null, 79022);
			addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 79023, 79024);
			addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 79025, 79027);
			addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 79028, 79029);
			addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 79030, 79032);
			addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 79033, 79036);
			addExpectedElement(null, 79037);
			addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 79038, 79039);
			addExpectedElement(null, 79040, 79048);
			addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 79049, 79060);
			addExpectedElement(null, 79061, 79081);
			addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 79082);
			addExpectedElement(null, 79083, 79084);
			addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 79085);
			addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 79086);
		}
		
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 79087);
		addExpectedElement(null, 79088);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 79089);
		addExpectedElement(null, 79090);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 79091);
		addExpectedElement(null, 79092);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 79093);
		addExpectedElement(null, 79094, 79095);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 79096);
		addExpectedElement(null, 79097, 79098);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 79099);
		addExpectedElement(null, 79100, 79101);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable(), 79102);
		addExpectedElement(null, 79103);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 79104);
		addExpectedElement(null, 79105, 79106);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField(), 79107);
		addExpectedElement(null, 79108);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 79109, 79110);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize(), 79111);
		addExpectedElement(null, 79112);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 79113, 79114);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 79115, 79117);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 79118, 79119);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 79120, 79122);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 79123, 79126);
		addExpectedElement(null, 79127);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 79128, 79129);
		addExpectedElement(null, 79130, 79138);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 79139, 79150);
		addExpectedElement(null, 79151, 79171);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 79172);
		addExpectedElement(null, 79173, 79174);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 79175);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 79176);
	}
	
;

parse_org_emftext_language_java_literals_NullLiteral returns [org.emftext.language.java.literals.NullLiteral element = null]
@init{
}
:
	a0 = 'null' {
		if (element == null) {
			element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createNullLiteral();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_121_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 79177, 79178);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 79179, 79181);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 79182, 79183);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 79184, 79186);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 79187, 79190);
		addExpectedElement(null, 79191);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 79192, 79193);
		addExpectedElement(null, 79194, 79202);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 79203, 79214);
		addExpectedElement(null, 79215, 79247);
	}
	
;

parse_org_emftext_language_java_modifiers_Public returns [org.emftext.language.java.modifiers.Public element = null]
@init{
}
:
	a0 = 'public' {
		if (element == null) {
			element = org.emftext.language.java.modifiers.ModifiersFactory.eINSTANCE.createPublic();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_122_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 79248, 79259);
		addExpectedElement(null, 79260);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 79261, 79272);
		addExpectedElement(null, 79273);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 79274, 79285);
		addExpectedElement(null, 79286);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 79287, 79298);
		addExpectedElement(null, 79299);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 79300, 79310);
		addExpectedElement(null, 79311);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 79312, 79323);
		addExpectedElement(null, 79324, 79325);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 79326, 79337);
		addExpectedElement(null, 79338);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 79339, 79350);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 79351, 79362);
		addExpectedElement(null, 79363);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 79364, 79375);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 79376, 79387);
		addExpectedElement(null, 79388);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 79389, 79400);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 79401, 79424);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 79425, 79448);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 79449, 79472);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 79473, 79496);
	}
	
;

parse_org_emftext_language_java_modifiers_Abstract returns [org.emftext.language.java.modifiers.Abstract element = null]
@init{
}
:
	a0 = 'abstract' {
		if (element == null) {
			element = org.emftext.language.java.modifiers.ModifiersFactory.eINSTANCE.createAbstract();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_123_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 79497, 79508);
		addExpectedElement(null, 79509);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 79510, 79521);
		addExpectedElement(null, 79522);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 79523, 79534);
		addExpectedElement(null, 79535);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 79536, 79547);
		addExpectedElement(null, 79548);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 79549, 79559);
		addExpectedElement(null, 79560);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 79561, 79572);
		addExpectedElement(null, 79573, 79574);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 79575, 79586);
		addExpectedElement(null, 79587);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 79588, 79599);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 79600, 79611);
		addExpectedElement(null, 79612);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 79613, 79624);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 79625, 79636);
		addExpectedElement(null, 79637);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 79638, 79649);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 79650, 79673);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 79674, 79697);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 79698, 79721);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 79722, 79745);
	}
	
;

parse_org_emftext_language_java_modifiers_Protected returns [org.emftext.language.java.modifiers.Protected element = null]
@init{
}
:
	a0 = 'protected' {
		if (element == null) {
			element = org.emftext.language.java.modifiers.ModifiersFactory.eINSTANCE.createProtected();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_124_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 79746, 79757);
		addExpectedElement(null, 79758);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 79759, 79770);
		addExpectedElement(null, 79771);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 79772, 79783);
		addExpectedElement(null, 79784);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 79785, 79796);
		addExpectedElement(null, 79797);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 79798, 79808);
		addExpectedElement(null, 79809);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 79810, 79821);
		addExpectedElement(null, 79822, 79823);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 79824, 79835);
		addExpectedElement(null, 79836);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 79837, 79848);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 79849, 79860);
		addExpectedElement(null, 79861);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 79862, 79873);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 79874, 79885);
		addExpectedElement(null, 79886);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 79887, 79898);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 79899, 79922);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 79923, 79946);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 79947, 79970);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 79971, 79994);
	}
	
;

parse_org_emftext_language_java_modifiers_Private returns [org.emftext.language.java.modifiers.Private element = null]
@init{
}
:
	a0 = 'private' {
		if (element == null) {
			element = org.emftext.language.java.modifiers.ModifiersFactory.eINSTANCE.createPrivate();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_125_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 79995, 80006);
		addExpectedElement(null, 80007);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 80008, 80019);
		addExpectedElement(null, 80020);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 80021, 80032);
		addExpectedElement(null, 80033);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 80034, 80045);
		addExpectedElement(null, 80046);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 80047, 80057);
		addExpectedElement(null, 80058);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 80059, 80070);
		addExpectedElement(null, 80071, 80072);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 80073, 80084);
		addExpectedElement(null, 80085);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 80086, 80097);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 80098, 80109);
		addExpectedElement(null, 80110);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 80111, 80122);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 80123, 80134);
		addExpectedElement(null, 80135);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 80136, 80147);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 80148, 80171);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 80172, 80195);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 80196, 80219);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 80220, 80243);
	}
	
;

parse_org_emftext_language_java_modifiers_Final returns [org.emftext.language.java.modifiers.Final element = null]
@init{
}
:
	a0 = 'final' {
		if (element == null) {
			element = org.emftext.language.java.modifiers.ModifiersFactory.eINSTANCE.createFinal();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_126_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 80244, 80255);
		addExpectedElement(null, 80256);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 80257, 80268);
		addExpectedElement(null, 80269);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 80270, 80281);
		addExpectedElement(null, 80282);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 80283, 80294);
		addExpectedElement(null, 80295);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 80296, 80306);
		addExpectedElement(null, 80307);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 80308, 80319);
		addExpectedElement(null, 80320, 80321);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 80322, 80333);
		addExpectedElement(null, 80334);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 80335, 80346);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 80347, 80358);
		addExpectedElement(null, 80359);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 80360, 80371);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 80372, 80383);
		addExpectedElement(null, 80384);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 80385, 80396);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 80397, 80420);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 80421, 80444);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 80445, 80468);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 80469, 80492);
	}
	
;

parse_org_emftext_language_java_modifiers_Static returns [org.emftext.language.java.modifiers.Static element = null]
@init{
}
:
	a0 = 'static' {
		if (element == null) {
			element = org.emftext.language.java.modifiers.ModifiersFactory.eINSTANCE.createStatic();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_127_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 80493, 80495);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 80496, 80507);
		addExpectedElement(null, 80508);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 80509, 80520);
		addExpectedElement(null, 80521);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 80522, 80533);
		addExpectedElement(null, 80534);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 80535, 80546);
		addExpectedElement(null, 80547);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 80548, 80558);
		addExpectedElement(null, 80559);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 80560, 80571);
		addExpectedElement(null, 80572, 80573);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 80574, 80585);
		addExpectedElement(null, 80586);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 80587, 80598);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 80599, 80610);
		addExpectedElement(null, 80611);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 80612, 80623);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 80624, 80635);
		addExpectedElement(null, 80636);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 80637, 80648);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 80649, 80672);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 80673, 80696);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 80697, 80720);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 80721, 80744);
	}
	
;

parse_org_emftext_language_java_modifiers_Native returns [org.emftext.language.java.modifiers.Native element = null]
@init{
}
:
	a0 = 'native' {
		if (element == null) {
			element = org.emftext.language.java.modifiers.ModifiersFactory.eINSTANCE.createNative();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_128_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 80745, 80756);
		addExpectedElement(null, 80757);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 80758, 80769);
		addExpectedElement(null, 80770);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 80771, 80782);
		addExpectedElement(null, 80783);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 80784, 80795);
		addExpectedElement(null, 80796);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 80797, 80807);
		addExpectedElement(null, 80808);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 80809, 80820);
		addExpectedElement(null, 80821, 80822);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 80823, 80834);
		addExpectedElement(null, 80835);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 80836, 80847);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 80848, 80859);
		addExpectedElement(null, 80860);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 80861, 80872);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 80873, 80884);
		addExpectedElement(null, 80885);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 80886, 80897);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 80898, 80921);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 80922, 80945);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 80946, 80969);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 80970, 80993);
	}
	
;

parse_org_emftext_language_java_modifiers_Synchronized returns [org.emftext.language.java.modifiers.Synchronized element = null]
@init{
}
:
	a0 = 'synchronized' {
		if (element == null) {
			element = org.emftext.language.java.modifiers.ModifiersFactory.eINSTANCE.createSynchronized();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_129_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 80994, 81005);
		addExpectedElement(null, 81006);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 81007, 81018);
		addExpectedElement(null, 81019);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 81020, 81031);
		addExpectedElement(null, 81032);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 81033, 81044);
		addExpectedElement(null, 81045);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 81046, 81056);
		addExpectedElement(null, 81057);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 81058, 81069);
		addExpectedElement(null, 81070, 81071);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 81072, 81083);
		addExpectedElement(null, 81084);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 81085, 81096);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 81097, 81108);
		addExpectedElement(null, 81109);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 81110, 81121);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 81122, 81133);
		addExpectedElement(null, 81134);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 81135, 81146);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 81147, 81170);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 81171, 81194);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 81195, 81218);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 81219, 81242);
	}
	
;

parse_org_emftext_language_java_modifiers_Transient returns [org.emftext.language.java.modifiers.Transient element = null]
@init{
}
:
	a0 = 'transient' {
		if (element == null) {
			element = org.emftext.language.java.modifiers.ModifiersFactory.eINSTANCE.createTransient();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_130_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 81243, 81254);
		addExpectedElement(null, 81255);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 81256, 81267);
		addExpectedElement(null, 81268);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 81269, 81280);
		addExpectedElement(null, 81281);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 81282, 81293);
		addExpectedElement(null, 81294);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 81295, 81305);
		addExpectedElement(null, 81306);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 81307, 81318);
		addExpectedElement(null, 81319, 81320);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 81321, 81332);
		addExpectedElement(null, 81333);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 81334, 81345);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 81346, 81357);
		addExpectedElement(null, 81358);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 81359, 81370);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 81371, 81382);
		addExpectedElement(null, 81383);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 81384, 81395);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 81396, 81419);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 81420, 81443);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 81444, 81467);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 81468, 81491);
	}
	
;

parse_org_emftext_language_java_modifiers_Volatile returns [org.emftext.language.java.modifiers.Volatile element = null]
@init{
}
:
	a0 = 'volatile' {
		if (element == null) {
			element = org.emftext.language.java.modifiers.ModifiersFactory.eINSTANCE.createVolatile();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_131_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 81492, 81503);
		addExpectedElement(null, 81504);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 81505, 81516);
		addExpectedElement(null, 81517);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 81518, 81529);
		addExpectedElement(null, 81530);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 81531, 81542);
		addExpectedElement(null, 81543);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 81544, 81554);
		addExpectedElement(null, 81555);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 81556, 81567);
		addExpectedElement(null, 81568, 81569);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 81570, 81581);
		addExpectedElement(null, 81582);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 81583, 81594);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 81595, 81606);
		addExpectedElement(null, 81607);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 81608, 81619);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 81620, 81631);
		addExpectedElement(null, 81632);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 81633, 81644);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 81645, 81668);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 81669, 81692);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 81693, 81716);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 81717, 81740);
	}
	
;

parse_org_emftext_language_java_modifiers_Strictfp returns [org.emftext.language.java.modifiers.Strictfp element = null]
@init{
}
:
	a0 = 'strictfp' {
		if (element == null) {
			element = org.emftext.language.java.modifiers.ModifiersFactory.eINSTANCE.createStrictfp();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_132_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), 81741, 81752);
		addExpectedElement(null, 81753);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), 81754, 81765);
		addExpectedElement(null, 81766);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), 81767, 81778);
		addExpectedElement(null, 81779);
		addExpectedElement(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), 81780, 81791);
		addExpectedElement(null, 81792);
		addExpectedElement(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), 81793, 81803);
		addExpectedElement(null, 81804);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), 81805, 81816);
		addExpectedElement(null, 81817, 81818);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 81819, 81830);
		addExpectedElement(null, 81831);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), 81832, 81843);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 81844, 81855);
		addExpectedElement(null, 81856);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), 81857, 81868);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 81869, 81880);
		addExpectedElement(null, 81881);
		addExpectedElement(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), 81882, 81893);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 81894, 81917);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 81918, 81941);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 81942, 81965);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 81966, 81989);
	}
	
;

parse_org_emftext_language_java_types_Void returns [org.emftext.language.java.types.Void element = null]
@init{
}
:
	a0 = 'void' {
		if (element == null) {
			element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createVoid();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_133_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 81990, 82004);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 82005);
		addExpectedElement(null, 82006, 82007);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 82008);
		addExpectedElement(null, 82009, 82010);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 82011);
		addExpectedElement(null, 82012, 82013);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 82014);
		addExpectedElement(null, 82015, 82018);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 82019);
		addExpectedElement(null, 82020);
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 82021);
		addExpectedElement(null, 82022);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 82023, 82024);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 82025, 82027);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 82028, 82029);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 82030, 82032);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 82033, 82036);
		addExpectedElement(null, 82037);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 82038, 82039);
		addExpectedElement(null, 82040, 82048);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 82049, 82060);
		addExpectedElement(null, 82061, 82081);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 82082);
		addExpectedElement(null, 82083, 82085);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 82086);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 82087);
	}
	
;

parse_org_emftext_language_java_types_Boolean returns [org.emftext.language.java.types.Boolean element = null]
@init{
}
:
	a0 = 'boolean' {
		if (element == null) {
			element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createBoolean();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_134_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 82088, 82102);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 82103);
		addExpectedElement(null, 82104, 82105);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 82106);
		addExpectedElement(null, 82107, 82108);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 82109);
		addExpectedElement(null, 82110, 82111);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 82112);
		addExpectedElement(null, 82113, 82116);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 82117);
		addExpectedElement(null, 82118);
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 82119);
		addExpectedElement(null, 82120);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 82121, 82122);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 82123, 82125);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 82126, 82127);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 82128, 82130);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 82131, 82134);
		addExpectedElement(null, 82135);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 82136, 82137);
		addExpectedElement(null, 82138, 82146);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 82147, 82158);
		addExpectedElement(null, 82159, 82179);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 82180);
		addExpectedElement(null, 82181, 82183);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 82184);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 82185);
	}
	
;

parse_org_emftext_language_java_types_Char returns [org.emftext.language.java.types.Char element = null]
@init{
}
:
	a0 = 'char' {
		if (element == null) {
			element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createChar();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_135_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 82186, 82200);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 82201);
		addExpectedElement(null, 82202, 82203);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 82204);
		addExpectedElement(null, 82205, 82206);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 82207);
		addExpectedElement(null, 82208, 82209);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 82210);
		addExpectedElement(null, 82211, 82214);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 82215);
		addExpectedElement(null, 82216);
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 82217);
		addExpectedElement(null, 82218);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 82219, 82220);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 82221, 82223);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 82224, 82225);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 82226, 82228);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 82229, 82232);
		addExpectedElement(null, 82233);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 82234, 82235);
		addExpectedElement(null, 82236, 82244);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 82245, 82256);
		addExpectedElement(null, 82257, 82277);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 82278);
		addExpectedElement(null, 82279, 82281);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 82282);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 82283);
	}
	
;

parse_org_emftext_language_java_types_Byte returns [org.emftext.language.java.types.Byte element = null]
@init{
}
:
	a0 = 'byte' {
		if (element == null) {
			element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createByte();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_136_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 82284, 82298);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 82299);
		addExpectedElement(null, 82300, 82301);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 82302);
		addExpectedElement(null, 82303, 82304);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 82305);
		addExpectedElement(null, 82306, 82307);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 82308);
		addExpectedElement(null, 82309, 82312);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 82313);
		addExpectedElement(null, 82314);
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 82315);
		addExpectedElement(null, 82316);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 82317, 82318);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 82319, 82321);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 82322, 82323);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 82324, 82326);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 82327, 82330);
		addExpectedElement(null, 82331);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 82332, 82333);
		addExpectedElement(null, 82334, 82342);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 82343, 82354);
		addExpectedElement(null, 82355, 82375);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 82376);
		addExpectedElement(null, 82377, 82379);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 82380);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 82381);
	}
	
;

parse_org_emftext_language_java_types_Short returns [org.emftext.language.java.types.Short element = null]
@init{
}
:
	a0 = 'short' {
		if (element == null) {
			element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createShort();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_137_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 82382, 82396);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 82397);
		addExpectedElement(null, 82398, 82399);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 82400);
		addExpectedElement(null, 82401, 82402);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 82403);
		addExpectedElement(null, 82404, 82405);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 82406);
		addExpectedElement(null, 82407, 82410);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 82411);
		addExpectedElement(null, 82412);
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 82413);
		addExpectedElement(null, 82414);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 82415, 82416);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 82417, 82419);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 82420, 82421);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 82422, 82424);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 82425, 82428);
		addExpectedElement(null, 82429);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 82430, 82431);
		addExpectedElement(null, 82432, 82440);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 82441, 82452);
		addExpectedElement(null, 82453, 82473);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 82474);
		addExpectedElement(null, 82475, 82477);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 82478);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 82479);
	}
	
;

parse_org_emftext_language_java_types_Int returns [org.emftext.language.java.types.Int element = null]
@init{
}
:
	a0 = 'int' {
		if (element == null) {
			element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createInt();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_138_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 82480, 82494);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 82495);
		addExpectedElement(null, 82496, 82497);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 82498);
		addExpectedElement(null, 82499, 82500);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 82501);
		addExpectedElement(null, 82502, 82503);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 82504);
		addExpectedElement(null, 82505, 82508);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 82509);
		addExpectedElement(null, 82510);
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 82511);
		addExpectedElement(null, 82512);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 82513, 82514);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 82515, 82517);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 82518, 82519);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 82520, 82522);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 82523, 82526);
		addExpectedElement(null, 82527);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 82528, 82529);
		addExpectedElement(null, 82530, 82538);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 82539, 82550);
		addExpectedElement(null, 82551, 82571);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 82572);
		addExpectedElement(null, 82573, 82575);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 82576);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 82577);
	}
	
;

parse_org_emftext_language_java_types_Long returns [org.emftext.language.java.types.Long element = null]
@init{
}
:
	a0 = 'long' {
		if (element == null) {
			element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createLong();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_139_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 82578, 82592);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 82593);
		addExpectedElement(null, 82594, 82595);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 82596);
		addExpectedElement(null, 82597, 82598);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 82599);
		addExpectedElement(null, 82600, 82601);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 82602);
		addExpectedElement(null, 82603, 82606);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 82607);
		addExpectedElement(null, 82608);
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 82609);
		addExpectedElement(null, 82610);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 82611, 82612);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 82613, 82615);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 82616, 82617);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 82618, 82620);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 82621, 82624);
		addExpectedElement(null, 82625);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 82626, 82627);
		addExpectedElement(null, 82628, 82636);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 82637, 82648);
		addExpectedElement(null, 82649, 82669);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 82670);
		addExpectedElement(null, 82671, 82673);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 82674);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 82675);
	}
	
;

parse_org_emftext_language_java_types_Float returns [org.emftext.language.java.types.Float element = null]
@init{
}
:
	a0 = 'float' {
		if (element == null) {
			element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createFloat();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_140_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 82676, 82690);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 82691);
		addExpectedElement(null, 82692, 82693);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 82694);
		addExpectedElement(null, 82695, 82696);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 82697);
		addExpectedElement(null, 82698, 82699);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 82700);
		addExpectedElement(null, 82701, 82704);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 82705);
		addExpectedElement(null, 82706);
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 82707);
		addExpectedElement(null, 82708);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 82709, 82710);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 82711, 82713);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 82714, 82715);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 82716, 82718);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 82719, 82722);
		addExpectedElement(null, 82723);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 82724, 82725);
		addExpectedElement(null, 82726, 82734);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 82735, 82746);
		addExpectedElement(null, 82747, 82767);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 82768);
		addExpectedElement(null, 82769, 82771);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 82772);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 82773);
	}
	
;

parse_org_emftext_language_java_types_Double returns [org.emftext.language.java.types.Double element = null]
@init{
}
:
	a0 = 'double' {
		if (element == null) {
			element = org.emftext.language.java.types.TypesFactory.eINSTANCE.createDouble();
			startIncompleteElement(element);
		}
		collectHiddenTokens(element);
		retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_141_0_0_0, null, true);
		copyLocalizationInfos((CommonToken)a0, element);
	}
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(null, 82774, 82788);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), 82789);
		addExpectedElement(null, 82790, 82791);
		addExpectedElement(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), 82792);
		addExpectedElement(null, 82793, 82794);
		addExpectedElement(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), 82795);
		addExpectedElement(null, 82796, 82797);
		addExpectedElement(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), 82798);
		addExpectedElement(null, 82799, 82802);
		addExpectedElement(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), 82803);
		addExpectedElement(null, 82804);
		addExpectedElement(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), 82805);
		addExpectedElement(null, 82806);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 82807, 82808);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 82809, 82811);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 82812, 82813);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 82814, 82816);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 82817, 82820);
		addExpectedElement(null, 82821);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 82822, 82823);
		addExpectedElement(null, 82824, 82832);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 82833, 82844);
		addExpectedElement(null, 82845, 82865);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), 82866);
		addExpectedElement(null, 82867, 82869);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), 82870);
		addExpectedElement(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), 82871);
	}
	
;

parse_org_emftext_language_java_literals_DecimalLongLiteral returns [org.emftext.language.java.literals.DecimalLongLiteral element = null]
@init{
}
:
	(
		a0 = DECIMAL_LONG_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createDecimalLongLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("DECIMAL_LONG_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_LONG_LITERAL__DECIMAL_VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.math.BigInteger resolved = (java.math.BigInteger) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_LONG_LITERAL__DECIMAL_VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_142_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 82872, 82873);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 82874, 82876);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 82877, 82878);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 82879, 82881);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 82882, 82885);
		addExpectedElement(null, 82886);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 82887, 82888);
		addExpectedElement(null, 82889, 82897);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 82898, 82909);
		addExpectedElement(null, 82910, 82942);
	}
	
;

parse_org_emftext_language_java_literals_DecimalFloatLiteral returns [org.emftext.language.java.literals.DecimalFloatLiteral element = null]
@init{
}
:
	(
		a0 = DECIMAL_FLOAT_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createDecimalFloatLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("DECIMAL_FLOAT_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_FLOAT_LITERAL__DECIMAL_VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.Float resolved = (java.lang.Float) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_FLOAT_LITERAL__DECIMAL_VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_143_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 82943, 82944);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 82945, 82947);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 82948, 82949);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 82950, 82952);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 82953, 82956);
		addExpectedElement(null, 82957);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 82958, 82959);
		addExpectedElement(null, 82960, 82968);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 82969, 82980);
		addExpectedElement(null, 82981, 83013);
	}
	
;

parse_org_emftext_language_java_literals_DecimalIntegerLiteral returns [org.emftext.language.java.literals.DecimalIntegerLiteral element = null]
@init{
}
:
	(
		a0 = DECIMAL_INTEGER_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createDecimalIntegerLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("DECIMAL_INTEGER_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_INTEGER_LITERAL__DECIMAL_VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.math.BigInteger resolved = (java.math.BigInteger) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_INTEGER_LITERAL__DECIMAL_VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_144_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 83014, 83015);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 83016, 83018);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 83019, 83020);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 83021, 83023);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 83024, 83027);
		addExpectedElement(null, 83028);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 83029, 83030);
		addExpectedElement(null, 83031, 83039);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 83040, 83051);
		addExpectedElement(null, 83052, 83084);
	}
	
;

parse_org_emftext_language_java_literals_DecimalDoubleLiteral returns [org.emftext.language.java.literals.DecimalDoubleLiteral element = null]
@init{
}
:
	(
		a0 = DECIMAL_DOUBLE_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createDecimalDoubleLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("DECIMAL_DOUBLE_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_DOUBLE_LITERAL__DECIMAL_VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.Double resolved = (java.lang.Double) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_DOUBLE_LITERAL__DECIMAL_VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_145_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 83085, 83086);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 83087, 83089);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 83090, 83091);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 83092, 83094);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 83095, 83098);
		addExpectedElement(null, 83099);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 83100, 83101);
		addExpectedElement(null, 83102, 83110);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 83111, 83122);
		addExpectedElement(null, 83123, 83155);
	}
	
;

parse_org_emftext_language_java_literals_HexLongLiteral returns [org.emftext.language.java.literals.HexLongLiteral element = null]
@init{
}
:
	(
		a0 = HEX_LONG_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createHexLongLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("HEX_LONG_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_LONG_LITERAL__HEX_VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.math.BigInteger resolved = (java.math.BigInteger) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_LONG_LITERAL__HEX_VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_146_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 83156, 83157);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 83158, 83160);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 83161, 83162);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 83163, 83165);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 83166, 83169);
		addExpectedElement(null, 83170);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 83171, 83172);
		addExpectedElement(null, 83173, 83181);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 83182, 83193);
		addExpectedElement(null, 83194, 83226);
	}
	
;

parse_org_emftext_language_java_literals_HexFloatLiteral returns [org.emftext.language.java.literals.HexFloatLiteral element = null]
@init{
}
:
	(
		a0 = HEX_FLOAT_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createHexFloatLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("HEX_FLOAT_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_FLOAT_LITERAL__HEX_VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.Float resolved = (java.lang.Float) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_FLOAT_LITERAL__HEX_VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_147_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 83227, 83228);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 83229, 83231);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 83232, 83233);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 83234, 83236);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 83237, 83240);
		addExpectedElement(null, 83241);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 83242, 83243);
		addExpectedElement(null, 83244, 83252);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 83253, 83264);
		addExpectedElement(null, 83265, 83297);
	}
	
;

parse_org_emftext_language_java_literals_HexDoubleLiteral returns [org.emftext.language.java.literals.HexDoubleLiteral element = null]
@init{
}
:
	(
		a0 = HEX_DOUBLE_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createHexDoubleLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("HEX_DOUBLE_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_DOUBLE_LITERAL__HEX_VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.Double resolved = (java.lang.Double) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_DOUBLE_LITERAL__HEX_VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_148_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 83298, 83299);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 83300, 83302);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 83303, 83304);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 83305, 83307);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 83308, 83311);
		addExpectedElement(null, 83312);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 83313, 83314);
		addExpectedElement(null, 83315, 83323);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 83324, 83335);
		addExpectedElement(null, 83336, 83368);
	}
	
;

parse_org_emftext_language_java_literals_HexIntegerLiteral returns [org.emftext.language.java.literals.HexIntegerLiteral element = null]
@init{
}
:
	(
		a0 = HEX_INTEGER_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createHexIntegerLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("HEX_INTEGER_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.math.BigInteger resolved = (java.math.BigInteger) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_149_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 83369, 83370);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 83371, 83373);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 83374, 83375);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 83376, 83378);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 83379, 83382);
		addExpectedElement(null, 83383);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 83384, 83385);
		addExpectedElement(null, 83386, 83394);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 83395, 83406);
		addExpectedElement(null, 83407, 83439);
	}
	
;

parse_org_emftext_language_java_literals_OctalLongLiteral returns [org.emftext.language.java.literals.OctalLongLiteral element = null]
@init{
}
:
	(
		a0 = OCTAL_LONG_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createOctalLongLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("OCTAL_LONG_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_LONG_LITERAL__OCTAL_VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.math.BigInteger resolved = (java.math.BigInteger) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_LONG_LITERAL__OCTAL_VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_150_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 83440, 83441);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 83442, 83444);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 83445, 83446);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 83447, 83449);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 83450, 83453);
		addExpectedElement(null, 83454);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 83455, 83456);
		addExpectedElement(null, 83457, 83465);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 83466, 83477);
		addExpectedElement(null, 83478, 83510);
	}
	
;

parse_org_emftext_language_java_literals_OctalIntegerLiteral returns [org.emftext.language.java.literals.OctalIntegerLiteral element = null]
@init{
}
:
	(
		a0 = OCTAL_INTEGER_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createOctalIntegerLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("OCTAL_INTEGER_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_INTEGER_LITERAL__OCTAL_VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.math.BigInteger resolved = (java.math.BigInteger) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_INTEGER_LITERAL__OCTAL_VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_151_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 83511, 83512);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 83513, 83515);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 83516, 83517);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 83518, 83520);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 83521, 83524);
		addExpectedElement(null, 83525);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 83526, 83527);
		addExpectedElement(null, 83528, 83536);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 83537, 83548);
		addExpectedElement(null, 83549, 83581);
	}
	
;

parse_org_emftext_language_java_literals_CharacterLiteral returns [org.emftext.language.java.literals.CharacterLiteral element = null]
@init{
}
:
	(
		a0 = CHARACTER_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createCharacterLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("CHARACTER_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.CHARACTER_LITERAL__VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.Character resolved = (java.lang.Character) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.CHARACTER_LITERAL__VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_152_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 83582, 83583);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 83584, 83586);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 83587, 83588);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 83589, 83591);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 83592, 83595);
		addExpectedElement(null, 83596);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 83597, 83598);
		addExpectedElement(null, 83599, 83607);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 83608, 83619);
		addExpectedElement(null, 83620, 83652);
	}
	
;

parse_org_emftext_language_java_literals_BooleanLiteral returns [org.emftext.language.java.literals.BooleanLiteral element = null]
@init{
}
:
	(
		a0 = BOOLEAN_LITERAL		
		{
			if (terminateParsing) {
				throw new org.emftext.language.java.resource.java.mopp.JavaTerminateParsingException();
			}
			if (element == null) {
				element = org.emftext.language.java.literals.LiteralsFactory.eINSTANCE.createBooleanLiteral();
				startIncompleteElement(element);
			}
			if (a0 != null) {
				org.emftext.language.java.resource.java.IJavaTokenResolver tokenResolver = tokenResolverFactory.createTokenResolver("BOOLEAN_LITERAL");
				tokenResolver.setOptions(getOptions());
				org.emftext.language.java.resource.java.IJavaTokenResolveResult result = getFreshTokenResolveResult();
				tokenResolver.resolve(a0.getText(), element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.BOOLEAN_LITERAL__VALUE), result);
				Object resolvedObject = result.getResolvedToken();
				if (resolvedObject == null) {
					addErrorToResource(result.getErrorMessage(), ((CommonToken) a0).getLine(), ((CommonToken) a0).getCharPositionInLine(), ((CommonToken) a0).getStartIndex(), ((CommonToken) a0).getStopIndex());
				}
				java.lang.Boolean resolved = (java.lang.Boolean) resolvedObject;
				if (resolved != null) {
					Object value = resolved;
					element.eSet(element.eClass().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.BOOLEAN_LITERAL__VALUE), value);
					completedElement(value, false);
				}
				collectHiddenTokens(element);
				retrieveLayoutInformation(element, org.emftext.language.java.resource.java.grammar.JavaGrammarInformationProvider.JAVA_153_0_0_0, resolved, true);
				copyLocalizationInfos((CommonToken) a0, element);
			}
		}
	)
	{
		// expected elements (follow set)
		// We've found the last token for this rule. The constructed EObject is now
		// complete.
		completedElement(element, true);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), 83653, 83654);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), 83655, 83657);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), 83658, 83659);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), 83660, 83662);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), 83663, 83666);
		addExpectedElement(null, 83667);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), 83668, 83669);
		addExpectedElement(null, 83670, 83678);
		addExpectedElement(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), 83679, 83690);
		addExpectedElement(null, 83691, 83723);
	}
	
;

parse_org_emftext_language_java_imports_Import returns [org.emftext.language.java.imports.Import element = null]
:
	c0 = parse_org_emftext_language_java_imports_ClassifierImport{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_imports_PackageImport{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_imports_StaticMemberImport{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_imports_StaticClassifierImport{ element = c3; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_classifiers_ConcreteClassifier returns [org.emftext.language.java.classifiers.ConcreteClassifier element = null]
:
	c0 = parse_org_emftext_language_java_classifiers_Class{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_classifiers_Interface{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_classifiers_Enumeration{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_classifiers_Annotation{ element = c3; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_modifiers_AnnotationInstanceOrModifier returns [org.emftext.language.java.modifiers.AnnotationInstanceOrModifier element = null]
:
	c0 = parse_org_emftext_language_java_annotations_AnnotationInstance{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_modifiers_Public{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_modifiers_Abstract{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_modifiers_Protected{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_org_emftext_language_java_modifiers_Private{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_org_emftext_language_java_modifiers_Final{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_org_emftext_language_java_modifiers_Static{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_org_emftext_language_java_modifiers_Native{ element = c7; /* this is a subclass or primitive expression choice */ }
	|	c8 = parse_org_emftext_language_java_modifiers_Synchronized{ element = c8; /* this is a subclass or primitive expression choice */ }
	|	c9 = parse_org_emftext_language_java_modifiers_Transient{ element = c9; /* this is a subclass or primitive expression choice */ }
	|	c10 = parse_org_emftext_language_java_modifiers_Volatile{ element = c10; /* this is a subclass or primitive expression choice */ }
	|	c11 = parse_org_emftext_language_java_modifiers_Strictfp{ element = c11; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_types_TypeReference returns [org.emftext.language.java.types.TypeReference element = null]
:
	c0 = parse_org_emftext_language_java_types_NamespaceClassifierReference{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_types_ClassifierReference{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_types_Void{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_types_Boolean{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_org_emftext_language_java_types_Char{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_org_emftext_language_java_types_Byte{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_org_emftext_language_java_types_Short{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_org_emftext_language_java_types_Int{ element = c7; /* this is a subclass or primitive expression choice */ }
	|	c8 = parse_org_emftext_language_java_types_Long{ element = c8; /* this is a subclass or primitive expression choice */ }
	|	c9 = parse_org_emftext_language_java_types_Float{ element = c9; /* this is a subclass or primitive expression choice */ }
	|	c10 = parse_org_emftext_language_java_types_Double{ element = c10; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_members_Member returns [org.emftext.language.java.members.Member element = null]
:
	c0 = parse_org_emftext_language_java_classifiers_Class{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_classifiers_Interface{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_classifiers_Enumeration{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_classifiers_Annotation{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_org_emftext_language_java_statements_Block{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_org_emftext_language_java_members_Constructor{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_org_emftext_language_java_members_InterfaceMethod{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_org_emftext_language_java_members_ClassMethod{ element = c7; /* this is a subclass or primitive expression choice */ }
	|	c8 = parse_org_emftext_language_java_annotations_AnnotationAttribute{ element = c8; /* this is a subclass or primitive expression choice */ }
	|	c9 = parse_org_emftext_language_java_members_Field{ element = c9; /* this is a subclass or primitive expression choice */ }
	|	c10 = parse_org_emftext_language_java_members_EmptyMember{ element = c10; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_annotations_AnnotationParameter returns [org.emftext.language.java.annotations.AnnotationParameter element = null]
:
	c0 = parse_org_emftext_language_java_annotations_SingleAnnotationParameter{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_annotations_AnnotationParameterList{ element = c1; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_modifiers_Modifier returns [org.emftext.language.java.modifiers.Modifier element = null]
:
	c0 = parse_org_emftext_language_java_modifiers_Public{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_modifiers_Abstract{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_modifiers_Protected{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_modifiers_Private{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_org_emftext_language_java_modifiers_Final{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_org_emftext_language_java_modifiers_Static{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_org_emftext_language_java_modifiers_Native{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_org_emftext_language_java_modifiers_Synchronized{ element = c7; /* this is a subclass or primitive expression choice */ }
	|	c8 = parse_org_emftext_language_java_modifiers_Transient{ element = c8; /* this is a subclass or primitive expression choice */ }
	|	c9 = parse_org_emftext_language_java_modifiers_Volatile{ element = c9; /* this is a subclass or primitive expression choice */ }
	|	c10 = parse_org_emftext_language_java_modifiers_Strictfp{ element = c10; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_statements_Statement returns [org.emftext.language.java.statements.Statement element = null]
:
	c0 = parse_org_emftext_language_java_classifiers_Class{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_classifiers_Interface{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_classifiers_Enumeration{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_classifiers_Annotation{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_org_emftext_language_java_statements_Block{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_org_emftext_language_java_statements_LocalVariableStatement{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_org_emftext_language_java_statements_Assert{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_org_emftext_language_java_statements_Condition{ element = c7; /* this is a subclass or primitive expression choice */ }
	|	c8 = parse_org_emftext_language_java_statements_ForLoop{ element = c8; /* this is a subclass or primitive expression choice */ }
	|	c9 = parse_org_emftext_language_java_statements_ForEachLoop{ element = c9; /* this is a subclass or primitive expression choice */ }
	|	c10 = parse_org_emftext_language_java_statements_WhileLoop{ element = c10; /* this is a subclass or primitive expression choice */ }
	|	c11 = parse_org_emftext_language_java_statements_DoWhileLoop{ element = c11; /* this is a subclass or primitive expression choice */ }
	|	c12 = parse_org_emftext_language_java_statements_EmptyStatement{ element = c12; /* this is a subclass or primitive expression choice */ }
	|	c13 = parse_org_emftext_language_java_statements_SynchronizedBlock{ element = c13; /* this is a subclass or primitive expression choice */ }
	|	c14 = parse_org_emftext_language_java_statements_TryBlock{ element = c14; /* this is a subclass or primitive expression choice */ }
	|	c15 = parse_org_emftext_language_java_statements_Switch{ element = c15; /* this is a subclass or primitive expression choice */ }
	|	c16 = parse_org_emftext_language_java_statements_Return{ element = c16; /* this is a subclass or primitive expression choice */ }
	|	c17 = parse_org_emftext_language_java_statements_Throw{ element = c17; /* this is a subclass or primitive expression choice */ }
	|	c18 = parse_org_emftext_language_java_statements_Break{ element = c18; /* this is a subclass or primitive expression choice */ }
	|	c19 = parse_org_emftext_language_java_statements_Continue{ element = c19; /* this is a subclass or primitive expression choice */ }
	|	c20 = parse_org_emftext_language_java_statements_JumpLabel{ element = c20; /* this is a subclass or primitive expression choice */ }
	|	c21 = parse_org_emftext_language_java_statements_ExpressionStatement{ element = c21; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_parameters_Parameter returns [org.emftext.language.java.parameters.Parameter element = null]
:
	c0 = parse_org_emftext_language_java_parameters_OrdinaryParameter{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_parameters_VariableLengthParameter{ element = c1; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_generics_TypeArgument returns [org.emftext.language.java.generics.TypeArgument element = null]
:
	c0 = parse_org_emftext_language_java_generics_QualifiedTypeArgument{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_generics_UnknownTypeArgument{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_generics_ExtendsTypeArgument{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_generics_SuperTypeArgument{ element = c3; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_references_Reference returns [org.emftext.language.java.references.Reference element = null]
:
	c0 = parse_org_emftext_language_java_annotations_AnnotationInstance{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_instantiations_NewConstructorCall{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_instantiations_ExplicitConstructorCall{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_arrays_ArrayInstantiationByValuesTyped{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_org_emftext_language_java_arrays_ArrayInstantiationByValuesUntyped{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_org_emftext_language_java_arrays_ArrayInstantiationBySize{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_org_emftext_language_java_references_MethodCall{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_org_emftext_language_java_references_IdentifierReference{ element = c7; /* this is a subclass or primitive expression choice */ }
	|	c8 = parse_org_emftext_language_java_references_ReflectiveClassReference{ element = c8; /* this is a subclass or primitive expression choice */ }
	|	c9 = parse_org_emftext_language_java_references_SelfReference{ element = c9; /* this is a subclass or primitive expression choice */ }
	|	c10 = parse_org_emftext_language_java_references_PrimitiveTypeReference{ element = c10; /* this is a subclass or primitive expression choice */ }
	|	c11 = parse_org_emftext_language_java_references_StringReference{ element = c11; /* this is a subclass or primitive expression choice */ }
	|	c12 = parse_org_emftext_language_java_expressions_NestedExpression{ element = c12; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_literals_Self returns [org.emftext.language.java.literals.Self element = null]
:
	c0 = parse_org_emftext_language_java_literals_This{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_literals_Super{ element = c1; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_types_PrimitiveType returns [org.emftext.language.java.types.PrimitiveType element = null]
:
	c0 = parse_org_emftext_language_java_types_Void{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_types_Boolean{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_types_Char{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_types_Byte{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_org_emftext_language_java_types_Short{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_org_emftext_language_java_types_Int{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_org_emftext_language_java_types_Long{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_org_emftext_language_java_types_Float{ element = c7; /* this is a subclass or primitive expression choice */ }
	|	c8 = parse_org_emftext_language_java_types_Double{ element = c8; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_statements_ForLoopInitializer returns [org.emftext.language.java.statements.ForLoopInitializer element = null]
:
	c0 = parse_org_emftext_language_java_variables_LocalVariable{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_expressions_ExpressionList{ element = c1; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_statements_SwitchCase returns [org.emftext.language.java.statements.SwitchCase element = null]
:
	c0 = parse_org_emftext_language_java_statements_NormalSwitchCase{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_statements_DefaultSwitchCase{ element = c1; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_operators_AssignmentOperator returns [org.emftext.language.java.operators.AssignmentOperator element = null]
:
	c0 = parse_org_emftext_language_java_operators_Assignment{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_operators_AssignmentPlus{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_operators_AssignmentMinus{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_operators_AssignmentMultiplication{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_org_emftext_language_java_operators_AssignmentDivision{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_org_emftext_language_java_operators_AssignmentAnd{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_org_emftext_language_java_operators_AssignmentOr{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_org_emftext_language_java_operators_AssignmentExclusiveOr{ element = c7; /* this is a subclass or primitive expression choice */ }
	|	c8 = parse_org_emftext_language_java_operators_AssignmentModulo{ element = c8; /* this is a subclass or primitive expression choice */ }
	|	c9 = parse_org_emftext_language_java_operators_AssignmentLeftShift{ element = c9; /* this is a subclass or primitive expression choice */ }
	|	c10 = parse_org_emftext_language_java_operators_AssignmentRightShift{ element = c10; /* this is a subclass or primitive expression choice */ }
	|	c11 = parse_org_emftext_language_java_operators_AssignmentUnsignedRightShift{ element = c11; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_operators_EqualityOperator returns [org.emftext.language.java.operators.EqualityOperator element = null]
:
	c0 = parse_org_emftext_language_java_operators_Equal{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_operators_NotEqual{ element = c1; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_operators_RelationOperator returns [org.emftext.language.java.operators.RelationOperator element = null]
:
	c0 = parse_org_emftext_language_java_operators_LessThan{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_operators_LessThanOrEqual{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_operators_GreaterThan{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_operators_GreaterThanOrEqual{ element = c3; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_operators_ShiftOperator returns [org.emftext.language.java.operators.ShiftOperator element = null]
:
	c0 = parse_org_emftext_language_java_operators_LeftShift{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_operators_RightShift{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_operators_UnsignedRightShift{ element = c2; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_operators_AdditiveOperator returns [org.emftext.language.java.operators.AdditiveOperator element = null]
:
	c0 = parse_org_emftext_language_java_operators_Addition{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_operators_Subtraction{ element = c1; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_operators_MultiplicativeOperator returns [org.emftext.language.java.operators.MultiplicativeOperator element = null]
:
	c0 = parse_org_emftext_language_java_operators_Multiplication{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_operators_Division{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_operators_Remainder{ element = c2; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_operators_UnaryOperator returns [org.emftext.language.java.operators.UnaryOperator element = null]
:
	c0 = parse_org_emftext_language_java_operators_Addition{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_operators_Subtraction{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_operators_Complement{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_operators_Negate{ element = c3; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_expressions_UnaryModificationExpression returns [org.emftext.language.java.expressions.UnaryModificationExpression element = null]
:
	c0 = parse_org_emftext_language_java_expressions_SuffixUnaryModificationExpression{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_expressions_PrefixUnaryModificationExpression{ element = c1; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_expressions_UnaryModificationExpressionChild returns [org.emftext.language.java.expressions.UnaryModificationExpressionChild element = null]
:
	c0 = parse_org_emftext_language_java_annotations_AnnotationInstance{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_instantiations_NewConstructorCall{ element = c1; /* this is a subclass or primitive expression choice */ }
	|	c2 = parse_org_emftext_language_java_instantiations_ExplicitConstructorCall{ element = c2; /* this is a subclass or primitive expression choice */ }
	|	c3 = parse_org_emftext_language_java_arrays_ArrayInstantiationByValuesTyped{ element = c3; /* this is a subclass or primitive expression choice */ }
	|	c4 = parse_org_emftext_language_java_arrays_ArrayInstantiationByValuesUntyped{ element = c4; /* this is a subclass or primitive expression choice */ }
	|	c5 = parse_org_emftext_language_java_arrays_ArrayInstantiationBySize{ element = c5; /* this is a subclass or primitive expression choice */ }
	|	c6 = parse_org_emftext_language_java_references_MethodCall{ element = c6; /* this is a subclass or primitive expression choice */ }
	|	c7 = parse_org_emftext_language_java_references_IdentifierReference{ element = c7; /* this is a subclass or primitive expression choice */ }
	|	c8 = parse_org_emftext_language_java_references_ReflectiveClassReference{ element = c8; /* this is a subclass or primitive expression choice */ }
	|	c9 = parse_org_emftext_language_java_references_SelfReference{ element = c9; /* this is a subclass or primitive expression choice */ }
	|	c10 = parse_org_emftext_language_java_references_PrimitiveTypeReference{ element = c10; /* this is a subclass or primitive expression choice */ }
	|	c11 = parse_org_emftext_language_java_references_StringReference{ element = c11; /* this is a subclass or primitive expression choice */ }
	|	c12 = parse_org_emftext_language_java_expressions_CastExpression{ element = c12; /* this is a subclass or primitive expression choice */ }
	|	c13 = parse_org_emftext_language_java_expressions_NestedExpression{ element = c13; /* this is a subclass or primitive expression choice */ }
	|	c14 = parse_org_emftext_language_java_literals_NullLiteral{ element = c14; /* this is a subclass or primitive expression choice */ }
	|	c15 = parse_org_emftext_language_java_literals_DecimalLongLiteral{ element = c15; /* this is a subclass or primitive expression choice */ }
	|	c16 = parse_org_emftext_language_java_literals_DecimalFloatLiteral{ element = c16; /* this is a subclass or primitive expression choice */ }
	|	c17 = parse_org_emftext_language_java_literals_DecimalIntegerLiteral{ element = c17; /* this is a subclass or primitive expression choice */ }
	|	c18 = parse_org_emftext_language_java_literals_DecimalDoubleLiteral{ element = c18; /* this is a subclass or primitive expression choice */ }
	|	c19 = parse_org_emftext_language_java_literals_HexLongLiteral{ element = c19; /* this is a subclass or primitive expression choice */ }
	|	c20 = parse_org_emftext_language_java_literals_HexFloatLiteral{ element = c20; /* this is a subclass or primitive expression choice */ }
	|	c21 = parse_org_emftext_language_java_literals_HexDoubleLiteral{ element = c21; /* this is a subclass or primitive expression choice */ }
	|	c22 = parse_org_emftext_language_java_literals_HexIntegerLiteral{ element = c22; /* this is a subclass or primitive expression choice */ }
	|	c23 = parse_org_emftext_language_java_literals_OctalLongLiteral{ element = c23; /* this is a subclass or primitive expression choice */ }
	|	c24 = parse_org_emftext_language_java_literals_OctalIntegerLiteral{ element = c24; /* this is a subclass or primitive expression choice */ }
	|	c25 = parse_org_emftext_language_java_literals_CharacterLiteral{ element = c25; /* this is a subclass or primitive expression choice */ }
	|	c26 = parse_org_emftext_language_java_literals_BooleanLiteral{ element = c26; /* this is a subclass or primitive expression choice */ }
	
;

parse_org_emftext_language_java_operators_UnaryModificationOperator returns [org.emftext.language.java.operators.UnaryModificationOperator element = null]
:
	c0 = parse_org_emftext_language_java_operators_PlusPlus{ element = c0; /* this is a subclass or primitive expression choice */ }
	|	c1 = parse_org_emftext_language_java_operators_MinusMinus{ element = c1; /* this is a subclass or primitive expression choice */ }
	
;

SL_COMMENT:
	('//'(~('\n'|'\r'|'\uffff'))* )
	{ _channel = 99; }
;
ML_COMMENT:
	('/*'.*'*/')
	{ _channel = 99; }
;
BOOLEAN_LITERAL:
	('true'|'false')
;
CHARACTER_LITERAL:
	('\''('\\'('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')|('\\''u'('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F'))|('\\'('0'..'3')('0'..'7')('0'..'7')|'\\'('0'..'7')('0'..'7')|'\\'('0'..'7'))|~('\''|'\\'))'\'')
;
STRING_LITERAL:
	('"'('\\'('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')|('\\''u'('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F')('0'..'9'|'a'..'f'|'A'..'F'))|'\\'('0'..'7')|~('\\'|'"'))*'"')
;
DECIMAL_LONG_LITERAL:
	(('0'|'1'..'9''0'..'9'*)('l'|'L'))
;
HEX_FLOAT_LITERAL:
	('0'('x'|'X')('0'..'9'|'a'..'f'|'A'..'F')*  (('.' ('0'..'9'|'a'..'f'|'A'..'F')+ (('p'|'P') ('+'|'-')? ('0'..'9')+ ('f'|'F') )?) | ((('p'|'P') ('+'|'-')? ('0'..'9')+ ('f'|'F') ))))
;
HEX_DOUBLE_LITERAL:
	('0'('x'|'X')('0'..'9'|'a'..'f'|'A'..'F')* (('.' ('0'..'9'|'a'..'f'|'A'..'F')+ (('p'|'P') ('+'|'-')? ('0'..'9')+ ('d'|'D')?)?) | ((('p'|'P') ('+'|'-')? ('0'..'9')+ ('d'|'D')?))))
;
HEX_LONG_LITERAL:
	('0'('x'|'X')('0'..'9'|'a'..'f'|'A'..'F')+ ('l'|'L'))
;
HEX_INTEGER_LITERAL:
	('0'('x'|'X')('0'..'9'|'a'..'f'|'A'..'F')+)
;
DECIMAL_FLOAT_LITERAL:
	(('0'..'9')+ '.' ('0'..'9')* (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+)? ('f'|'F') | ('.' ('0'..'9')+ (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+)?) ('f'|'F') | (('0'..'9')+ (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+) ('f'|'F') | ('0'..'9')+ ('f'|'F')))
;
DECIMAL_DOUBLE_LITERAL:
	(('0'..'9')+ '.' ('0'..'9')* (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+)? ('d'|'D')? | ('.' ('0'..'9')+ (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+)?) ('d'|'D')? | (('0'..'9')+ (('e'|'E'|'p'|'P') ('+'|'-')? ('0'..'9')+) ('d'|'D')? | ('0'..'9')+ ('d'|'D')))
;
DECIMAL_INTEGER_LITERAL:
	(('0'|'1'..'9''0'..'9'*))
;
OCTAL_LONG_LITERAL:
	('0'('0'..'7')+('l'|'L'))
;
OCTAL_INTEGER_LITERAL:
	('0'('0'..'7')+)
;
IDENTIFIER:
	(('\u0024'|'\u0041'..'\u005a'|'\u005f'|'\u0061'..'\u007a'|'\u00a2'..'\u00a5'|'\u00aa'|'\u00b5'|'\u00ba'|'\u00c0'..'\u00d6'|'\u00d8'..'\u00f6'|'\u00f8'..'\u0236'|'\u0250'..'\u02c1'|'\u02c6'..'\u02d1'|'\u02e0'..'\u02e4'|'\u02ee'|'\u037a'|'\u0386'|'\u0388'..'\u038a'|'\u038c'|'\u038e'..'\u03a1'|'\u03a3'..'\u03ce'|'\u03d0'..'\u03f5'|'\u03f7'..'\u03fb'|'\u0400'..'\u0481'|'\u048a'..'\u04ce'|'\u04d0'..'\u04f5'|'\u04f8'..'\u04f9'|'\u0500'..'\u050f'|'\u0531'..'\u0556'|'\u0559'|'\u0561'..'\u0587'|'\u05d0'..'\u05ea'|'\u05f0'..'\u05f2'|'\u0621'..'\u063a'|'\u0640'..'\u064a'|'\u066e'..'\u066f'|'\u0671'..'\u06d3'|'\u06d5'|'\u06e5'..'\u06e6'|'\u06ee'..'\u06ef'|'\u06fa'..'\u06fc'|'\u06ff'|'\u0710'|'\u0712'..'\u072f'|'\u074d'..'\u074f'|'\u0780'..'\u07a5'|'\u07b1'|'\u0904'..'\u0939'|'\u093d'|'\u0950'|'\u0958'..'\u0961'|'\u0985'..'\u098c'|'\u098f'..'\u0990'|'\u0993'..'\u09a8'|'\u09aa'..'\u09b0'|'\u09b2'|'\u09b6'..'\u09b9'|'\u09bd'|'\u09dc'..'\u09dd'|'\u09df'..'\u09e1'|'\u09f0'..'\u09f3'|'\u0a05'..'\u0a0a'|'\u0a0f'..'\u0a10'|'\u0a13'..'\u0a28'|'\u0a2a'..'\u0a30'|'\u0a32'..'\u0a33'|'\u0a35'..'\u0a36'|'\u0a38'..'\u0a39'|'\u0a59'..'\u0a5c'|'\u0a5e'|'\u0a72'..'\u0a74'|'\u0a85'..'\u0a8d'|'\u0a8f'..'\u0a91'|'\u0a93'..'\u0aa8'|'\u0aaa'..'\u0ab0'|'\u0ab2'..'\u0ab3'|'\u0ab5'..'\u0ab9'|'\u0abd'|'\u0ad0'|'\u0ae0'..'\u0ae1'|'\u0af1'|'\u0b05'..'\u0b0c'|'\u0b0f'..'\u0b10'|'\u0b13'..'\u0b28'|'\u0b2a'..'\u0b30'|'\u0b32'..'\u0b33'|'\u0b35'..'\u0b39'|'\u0b3d'|'\u0b5c'..'\u0b5d'|'\u0b5f'..'\u0b61'|'\u0b71'|'\u0b83'|'\u0b85'..'\u0b8a'|'\u0b8e'..'\u0b90'|'\u0b92'..'\u0b95'|'\u0b99'..'\u0b9a'|'\u0b9c'|'\u0b9e'..'\u0b9f'|'\u0ba3'..'\u0ba4'|'\u0ba8'..'\u0baa'|'\u0bae'..'\u0bb5'|'\u0bb7'..'\u0bb9'|'\u0bf9'|'\u0c05'..'\u0c0c'|'\u0c0e'..'\u0c10'|'\u0c12'..'\u0c28'|'\u0c2a'..'\u0c33'|'\u0c35'..'\u0c39'|'\u0c60'..'\u0c61'|'\u0c85'..'\u0c8c'|'\u0c8e'..'\u0c90'|'\u0c92'..'\u0ca8'|'\u0caa'..'\u0cb3'|'\u0cb5'..'\u0cb9'|'\u0cbd'|'\u0cde'|'\u0ce0'..'\u0ce1'|'\u0d05'..'\u0d0c'|'\u0d0e'..'\u0d10'|'\u0d12'..'\u0d28'|'\u0d2a'..'\u0d39'|'\u0d60'..'\u0d61'|'\u0d85'..'\u0d96'|'\u0d9a'..'\u0db1'|'\u0db3'..'\u0dbb'|'\u0dbd'|'\u0dc0'..'\u0dc6'|'\u0e01'..'\u0e30'|'\u0e32'..'\u0e33'|'\u0e3f'..'\u0e46'|'\u0e81'..'\u0e82'|'\u0e84'|'\u0e87'..'\u0e88'|'\u0e8a'|'\u0e8d'|'\u0e94'..'\u0e97'|'\u0e99'..'\u0e9f'|'\u0ea1'..'\u0ea3'|'\u0ea5'|'\u0ea7'|'\u0eaa'..'\u0eab'|'\u0ead'..'\u0eb0'|'\u0eb2'..'\u0eb3'|'\u0ebd'|'\u0ec0'..'\u0ec4'|'\u0ec6'|'\u0edc'..'\u0edd'|'\u0f00'|'\u0f40'..'\u0f47'|'\u0f49'..'\u0f6a'|'\u0f88'..'\u0f8b'|'\u1000'..'\u1021'|'\u1023'..'\u1027'|'\u1029'..'\u102a'|'\u1050'..'\u1055'|'\u10a0'..'\u10c5'|'\u10d0'..'\u10f8'|'\u1100'..'\u1159'|'\u115f'..'\u11a2'|'\u11a8'..'\u11f9'|'\u1200'..'\u1206'|'\u1208'..'\u1246'|'\u1248'|'\u124a'..'\u124d'|'\u1250'..'\u1256'|'\u1258'|'\u125a'..'\u125d'|'\u1260'..'\u1286'|'\u1288'|'\u128a'..'\u128d'|'\u1290'..'\u12ae'|'\u12b0'|'\u12b2'..'\u12b5'|'\u12b8'..'\u12be'|'\u12c0'|'\u12c2'..'\u12c5'|'\u12c8'..'\u12ce'|'\u12d0'..'\u12d6'|'\u12d8'..'\u12ee'|'\u12f0'..'\u130e'|'\u1310'|'\u1312'..'\u1315'|'\u1318'..'\u131e'|'\u1320'..'\u1346'|'\u1348'..'\u135a'|'\u13a0'..'\u13f4'|'\u1401'..'\u166c'|'\u166f'..'\u1676'|'\u1681'..'\u169a'|'\u16a0'..'\u16ea'|'\u16ee'..'\u16f0'|'\u1700'..'\u170c'|'\u170e'..'\u1711'|'\u1720'..'\u1731'|'\u1740'..'\u1751'|'\u1760'..'\u176c'|'\u176e'..'\u1770'|'\u1780'..'\u17b3'|'\u17d7'|'\u17db'..'\u17dc'|'\u1820'..'\u1877'|'\u1880'..'\u18a8'|'\u1900'..'\u191c'|'\u1950'..'\u196d'|'\u1970'..'\u1974'|'\u1d00'..'\u1d6b'|'\u1e00'..'\u1e9b'|'\u1ea0'..'\u1ef9'|'\u1f00'..'\u1f15'|'\u1f18'..'\u1f1d'|'\u1f20'..'\u1f45'|'\u1f48'..'\u1f4d'|'\u1f50'..'\u1f57'|'\u1f59'|'\u1f5b'|'\u1f5d'|'\u1f5f'..'\u1f7d'|'\u1f80'..'\u1fb4'|'\u1fb6'..'\u1fbc'|'\u1fbe'|'\u1fc2'..'\u1fc4'|'\u1fc6'..'\u1fcc'|'\u1fd0'..'\u1fd3'|'\u1fd6'..'\u1fdb'|'\u1fe0'..'\u1fec'|'\u1ff2'..'\u1ff4'|'\u1ff6'..'\u1ffc'|'\u203f'..'\u2040'|'\u2054'|'\u2071'|'\u207f'|'\u20a0'..'\u20b1'|'\u2102'|'\u2107'|'\u210a'..'\u2113'|'\u2115'|'\u2119'..'\u211d'|'\u2124'|'\u2126'|'\u2128'|'\u212a'..'\u212d'|'\u212f'..'\u2131'|'\u2133'..'\u2139'|'\u213d'..'\u213f'|'\u2145'..'\u2149'|'\u2160'..'\u2183'|'\u3005'..'\u3007'|'\u3021'..'\u3029'|'\u3031'..'\u3035'|'\u3038'..'\u303c'|'\u3041'..'\u3096'|'\u309d'..'\u309f'|'\u30a1'..'\u30ff'|'\u3105'..'\u312c'|'\u3131'..'\u318e'|'\u31a0'..'\u31b7'|'\u31f0'..'\u31ff'|'\u3400'..'\u4db5'|'\u4e00'..'\u9fa5'|'\ua000'..'\ua48c'|'\uac00'..'\ud7a3'|'\ud800'..'\udbff'|'\uf900'..'\ufa2d'|'\ufa30'..'\ufa6a'|'\ufb00'..'\ufb06'|'\ufb13'..'\ufb17'|'\ufb1d'|'\ufb1f'..'\ufb28'|'\ufb2a'..'\ufb36'|'\ufb38'..'\ufb3c'|'\ufb3e'|'\ufb40'..'\ufb41'|'\ufb43'..'\ufb44'|'\ufb46'..'\ufbb1'|'\ufbd3'..'\ufd3d'|'\ufd50'..'\ufd8f'|'\ufd92'..'\ufdc7'|'\ufdf0'..'\ufdfc'|'\ufe33'..'\ufe34'|'\ufe4d'..'\ufe4f'|'\ufe69'|'\ufe70'..'\ufe74'|'\ufe76'..'\ufefc'|'\uff04'|'\uff21'..'\uff3a'|'\uff3f'|'\uff41'..'\uff5a'|'\uff65'..'\uffbe'|'\uffc2'..'\uffc7'|'\uffca'..'\uffcf'|'\uffd2'..'\uffd7'|'\uffda'..'\uffdc'|'\uffe0'..'\uffe1'|'\uffe5'..'\uffe6')('\u0000'..'\u0008'|'\u000e'..'\u001b'|'\u0024'|'\u0030'..'\u0039'|'\u0041'..'\u005a'|'\u005f'|'\u0061'..'\u007a'|'\u007f'..'\u009f'|'\u00a2'..'\u00a5'|'\u00aa'|'\u00ad'|'\u00b5'|'\u00ba'|'\u00c0'..'\u00d6'|'\u00d8'..'\u00f6'|'\u00f8'..'\u0236'|'\u0250'..'\u02c1'|'\u02c6'..'\u02d1'|'\u02e0'..'\u02e4'|'\u02ee'|'\u0300'..'\u0357'|'\u035d'..'\u036f'|'\u037a'|'\u0386'|'\u0388'..'\u038a'|'\u038c'|'\u038e'..'\u03a1'|'\u03a3'..'\u03ce'|'\u03d0'..'\u03f5'|'\u03f7'..'\u03fb'|'\u0400'..'\u0481'|'\u0483'..'\u0486'|'\u048a'..'\u04ce'|'\u04d0'..'\u04f5'|'\u04f8'..'\u04f9'|'\u0500'..'\u050f'|'\u0531'..'\u0556'|'\u0559'|'\u0561'..'\u0587'|'\u0591'..'\u05a1'|'\u05a3'..'\u05b9'|'\u05bb'..'\u05bd'|'\u05bf'|'\u05c1'..'\u05c2'|'\u05c4'|'\u05d0'..'\u05ea'|'\u05f0'..'\u05f2'|'\u0600'..'\u0603'|'\u0610'..'\u0615'|'\u0621'..'\u063a'|'\u0640'..'\u0658'|'\u0660'..'\u0669'|'\u066e'..'\u06d3'|'\u06d5'..'\u06dd'|'\u06df'..'\u06e8'|'\u06ea'..'\u06fc'|'\u06ff'|'\u070f'..'\u074a'|'\u074d'..'\u074f'|'\u0780'..'\u07b1'|'\u0901'..'\u0939'|'\u093c'..'\u094d'|'\u0950'..'\u0954'|'\u0958'..'\u0963'|'\u0966'..'\u096f'|'\u0981'..'\u0983'|'\u0985'..'\u098c'|'\u098f'..'\u0990'|'\u0993'..'\u09a8'|'\u09aa'..'\u09b0'|'\u09b2'|'\u09b6'..'\u09b9'|'\u09bc'..'\u09c4'|'\u09c7'..'\u09c8'|'\u09cb'..'\u09cd'|'\u09d7'|'\u09dc'..'\u09dd'|'\u09df'..'\u09e3'|'\u09e6'..'\u09f3'|'\u0a01'..'\u0a03'|'\u0a05'..'\u0a0a'|'\u0a0f'..'\u0a10'|'\u0a13'..'\u0a28'|'\u0a2a'..'\u0a30'|'\u0a32'..'\u0a33'|'\u0a35'..'\u0a36'|'\u0a38'..'\u0a39'|'\u0a3c'|'\u0a3e'..'\u0a42'|'\u0a47'..'\u0a48'|'\u0a4b'..'\u0a4d'|'\u0a59'..'\u0a5c'|'\u0a5e'|'\u0a66'..'\u0a74'|'\u0a81'..'\u0a83'|'\u0a85'..'\u0a8d'|'\u0a8f'..'\u0a91'|'\u0a93'..'\u0aa8'|'\u0aaa'..'\u0ab0'|'\u0ab2'..'\u0ab3'|'\u0ab5'..'\u0ab9'|'\u0abc'..'\u0ac5'|'\u0ac7'..'\u0ac9'|'\u0acb'..'\u0acd'|'\u0ad0'|'\u0ae0'..'\u0ae3'|'\u0ae6'..'\u0aef'|'\u0af1'|'\u0b01'..'\u0b03'|'\u0b05'..'\u0b0c'|'\u0b0f'..'\u0b10'|'\u0b13'..'\u0b28'|'\u0b2a'..'\u0b30'|'\u0b32'..'\u0b33'|'\u0b35'..'\u0b39'|'\u0b3c'..'\u0b43'|'\u0b47'..'\u0b48'|'\u0b4b'..'\u0b4d'|'\u0b56'..'\u0b57'|'\u0b5c'..'\u0b5d'|'\u0b5f'..'\u0b61'|'\u0b66'..'\u0b6f'|'\u0b71'|'\u0b82'..'\u0b83'|'\u0b85'..'\u0b8a'|'\u0b8e'..'\u0b90'|'\u0b92'..'\u0b95'|'\u0b99'..'\u0b9a'|'\u0b9c'|'\u0b9e'..'\u0b9f'|'\u0ba3'..'\u0ba4'|'\u0ba8'..'\u0baa'|'\u0bae'..'\u0bb5'|'\u0bb7'..'\u0bb9'|'\u0bbe'..'\u0bc2'|'\u0bc6'..'\u0bc8'|'\u0bca'..'\u0bcd'|'\u0bd7'|'\u0be7'..'\u0bef'|'\u0bf9'|'\u0c01'..'\u0c03'|'\u0c05'..'\u0c0c'|'\u0c0e'..'\u0c10'|'\u0c12'..'\u0c28'|'\u0c2a'..'\u0c33'|'\u0c35'..'\u0c39'|'\u0c3e'..'\u0c44'|'\u0c46'..'\u0c48'|'\u0c4a'..'\u0c4d'|'\u0c55'..'\u0c56'|'\u0c60'..'\u0c61'|'\u0c66'..'\u0c6f'|'\u0c82'..'\u0c83'|'\u0c85'..'\u0c8c'|'\u0c8e'..'\u0c90'|'\u0c92'..'\u0ca8'|'\u0caa'..'\u0cb3'|'\u0cb5'..'\u0cb9'|'\u0cbc'..'\u0cc4'|'\u0cc6'..'\u0cc8'|'\u0cca'..'\u0ccd'|'\u0cd5'..'\u0cd6'|'\u0cde'|'\u0ce0'..'\u0ce1'|'\u0ce6'..'\u0cef'|'\u0d02'..'\u0d03'|'\u0d05'..'\u0d0c'|'\u0d0e'..'\u0d10'|'\u0d12'..'\u0d28'|'\u0d2a'..'\u0d39'|'\u0d3e'..'\u0d43'|'\u0d46'..'\u0d48'|'\u0d4a'..'\u0d4d'|'\u0d57'|'\u0d60'..'\u0d61'|'\u0d66'..'\u0d6f'|'\u0d82'..'\u0d83'|'\u0d85'..'\u0d96'|'\u0d9a'..'\u0db1'|'\u0db3'..'\u0dbb'|'\u0dbd'|'\u0dc0'..'\u0dc6'|'\u0dca'|'\u0dcf'..'\u0dd4'|'\u0dd6'|'\u0dd8'..'\u0ddf'|'\u0df2'..'\u0df3'|'\u0e01'..'\u0e3a'|'\u0e3f'..'\u0e4e'|'\u0e50'..'\u0e59'|'\u0e81'..'\u0e82'|'\u0e84'|'\u0e87'..'\u0e88'|'\u0e8a'|'\u0e8d'|'\u0e94'..'\u0e97'|'\u0e99'..'\u0e9f'|'\u0ea1'..'\u0ea3'|'\u0ea5'|'\u0ea7'|'\u0eaa'..'\u0eab'|'\u0ead'..'\u0eb9'|'\u0ebb'..'\u0ebd'|'\u0ec0'..'\u0ec4'|'\u0ec6'|'\u0ec8'..'\u0ecd'|'\u0ed0'..'\u0ed9'|'\u0edc'..'\u0edd'|'\u0f00'|'\u0f18'..'\u0f19'|'\u0f20'..'\u0f29'|'\u0f35'|'\u0f37'|'\u0f39'|'\u0f3e'..'\u0f47'|'\u0f49'..'\u0f6a'|'\u0f71'..'\u0f84'|'\u0f86'..'\u0f8b'|'\u0f90'..'\u0f97'|'\u0f99'..'\u0fbc'|'\u0fc6'|'\u1000'..'\u1021'|'\u1023'..'\u1027'|'\u1029'..'\u102a'|'\u102c'..'\u1032'|'\u1036'..'\u1039'|'\u1040'..'\u1049'|'\u1050'..'\u1059'|'\u10a0'..'\u10c5'|'\u10d0'..'\u10f8'|'\u1100'..'\u1159'|'\u115f'..'\u11a2'|'\u11a8'..'\u11f9'|'\u1200'..'\u1206'|'\u1208'..'\u1246'|'\u1248'|'\u124a'..'\u124d'|'\u1250'..'\u1256'|'\u1258'|'\u125a'..'\u125d'|'\u1260'..'\u1286'|'\u1288'|'\u128a'..'\u128d'|'\u1290'..'\u12ae'|'\u12b0'|'\u12b2'..'\u12b5'|'\u12b8'..'\u12be'|'\u12c0'|'\u12c2'..'\u12c5'|'\u12c8'..'\u12ce'|'\u12d0'..'\u12d6'|'\u12d8'..'\u12ee'|'\u12f0'..'\u130e'|'\u1310'|'\u1312'..'\u1315'|'\u1318'..'\u131e'|'\u1320'..'\u1346'|'\u1348'..'\u135a'|'\u1369'..'\u1371'|'\u13a0'..'\u13f4'|'\u1401'..'\u166c'|'\u166f'..'\u1676'|'\u1681'..'\u169a'|'\u16a0'..'\u16ea'|'\u16ee'..'\u16f0'|'\u1700'..'\u170c'|'\u170e'..'\u1714'|'\u1720'..'\u1734'|'\u1740'..'\u1753'|'\u1760'..'\u176c'|'\u176e'..'\u1770'|'\u1772'..'\u1773'|'\u1780'..'\u17d3'|'\u17d7'|'\u17db'..'\u17dd'|'\u17e0'..'\u17e9'|'\u180b'..'\u180d'|'\u1810'..'\u1819'|'\u1820'..'\u1877'|'\u1880'..'\u18a9'|'\u1900'..'\u191c'|'\u1920'..'\u192b'|'\u1930'..'\u193b'|'\u1946'..'\u196d'|'\u1970'..'\u1974'|'\u1d00'..'\u1d6b'|'\u1e00'..'\u1e9b'|'\u1ea0'..'\u1ef9'|'\u1f00'..'\u1f15'|'\u1f18'..'\u1f1d'|'\u1f20'..'\u1f45'|'\u1f48'..'\u1f4d'|'\u1f50'..'\u1f57'|'\u1f59'|'\u1f5b'|'\u1f5d'|'\u1f5f'..'\u1f7d'|'\u1f80'..'\u1fb4'|'\u1fb6'..'\u1fbc'|'\u1fbe'|'\u1fc2'..'\u1fc4'|'\u1fc6'..'\u1fcc'|'\u1fd0'..'\u1fd3'|'\u1fd6'..'\u1fdb'|'\u1fe0'..'\u1fec'|'\u1ff2'..'\u1ff4'|'\u1ff6'..'\u1ffc'|'\u200c'..'\u200f'|'\u202a'..'\u202e'|'\u203f'..'\u2040'|'\u2054'|'\u2060'..'\u2063'|'\u206a'..'\u206f'|'\u2071'|'\u207f'|'\u20a0'..'\u20b1'|'\u20d0'..'\u20dc'|'\u20e1'|'\u20e5'..'\u20ea'|'\u2102'|'\u2107'|'\u210a'..'\u2113'|'\u2115'|'\u2119'..'\u211d'|'\u2124'|'\u2126'|'\u2128'|'\u212a'..'\u212d'|'\u212f'..'\u2131'|'\u2133'..'\u2139'|'\u213d'..'\u213f'|'\u2145'..'\u2149'|'\u2160'..'\u2183'|'\u3005'..'\u3007'|'\u3021'..'\u302f'|'\u3031'..'\u3035'|'\u3038'..'\u303c'|'\u3041'..'\u3096'|'\u3099'..'\u309a'|'\u309d'..'\u309f'|'\u30a1'..'\u30ff'|'\u3105'..'\u312c'|'\u3131'..'\u318e'|'\u31a0'..'\u31b7'|'\u31f0'..'\u31ff'|'\u3400'..'\u4db5'|'\u4e00'..'\u9fa5'|'\ua000'..'\ua48c'|'\uac00'..'\ud7a3'|'\ud800'..'\udfff'|'\uf900'..'\ufa2d'|'\ufa30'..'\ufa6a'|'\ufb00'..'\ufb06'|'\ufb13'..'\ufb17'|'\ufb1d'..'\ufb28'|'\ufb2a'..'\ufb36'|'\ufb38'..'\ufb3c'|'\ufb3e'|'\ufb40'..'\ufb41'|'\ufb43'..'\ufb44'|'\ufb46'..'\ufbb1'|'\ufbd3'..'\ufd3d'|'\ufd50'..'\ufd8f'|'\ufd92'..'\ufdc7'|'\ufdf0'..'\ufdfc'|'\ufe00'..'\ufe0f'|'\ufe20'..'\ufe23'|'\ufe33'..'\ufe34'|'\ufe4d'..'\ufe4f'|'\ufe69'|'\ufe70'..'\ufe74'|'\ufe76'..'\ufefc'|'\ufeff'|'\uff04'|'\uff10'..'\uff19'|'\uff21'..'\uff3a'|'\uff3f'|'\uff41'..'\uff5a'|'\uff65'..'\uffbe'|'\uffc2'..'\uffc7'|'\uffca'..'\uffcf'|'\uffd2'..'\uffd7'|'\uffda'..'\uffdc'|'\uffe0'..'\uffe1'|'\uffe5'..'\uffe6'|'\ufff9'..'\ufffb')*)
;
WHITESPACE:
	((' '|'\t'|'\f'|'\r'|'\n')+)
	{ _channel = 99; }
;

