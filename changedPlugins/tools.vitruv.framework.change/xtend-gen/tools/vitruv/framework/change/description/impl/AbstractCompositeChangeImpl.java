package tools.vitruv.framework.change.description.impl;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import tools.vitruv.framework.change.description.CompositeChange;
import tools.vitruv.framework.change.description.VitruviusChange;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.interaction.UserInteractionBase;
import tools.vitruv.framework.util.datatypes.VURI;
import tools.vitruv.framework.uuid.UuidResolver;

@SuppressWarnings("all")
public abstract class AbstractCompositeChangeImpl<C extends VitruviusChange> implements CompositeChange<C> {
  private List<C> changes;
  
  public AbstractCompositeChangeImpl() {
    LinkedList<C> _linkedList = new LinkedList<C>();
    this.changes = _linkedList;
  }
  
  public AbstractCompositeChangeImpl(final List<? extends C> changes) {
    LinkedList<C> _linkedList = new LinkedList<C>(changes);
    this.changes = _linkedList;
  }
  
  @Override
  public List<C> getChanges() {
    return this.changes;
  }
  
  @Override
  public void addChange(final C change) {
    if ((change != null)) {
      this.changes.add(change);
    }
  }
  
  @Override
  public void removeChange(final C change) {
    if ((change != null)) {
      this.changes.remove(change);
    }
  }
  
  @Override
  public boolean containsConcreteChange() {
    for (final C change : this.changes) {
      boolean _containsConcreteChange = change.containsConcreteChange();
      if (_containsConcreteChange) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public VURI getURI() {
    final Function1<C, VURI> _function = (C it) -> {
      return it.getURI();
    };
    final Iterable<VURI> uris = IterableExtensions.<VURI>filterNull(ListExtensions.<C, VURI>map(this.changes, _function));
    boolean _isEmpty = IterableExtensions.isEmpty(uris);
    boolean _not = (!_isEmpty);
    if (_not) {
      return ((VURI[])Conversions.unwrapArray(uris, VURI.class))[0];
    } else {
      return null;
    }
  }
  
  @Override
  public boolean validate() {
    boolean _containsConcreteChange = this.containsConcreteChange();
    boolean _not = (!_containsConcreteChange);
    if (_not) {
      return false;
    }
    VURI lastURI = this.changes.get(0).getURI();
    for (final C change : this.changes) {
      {
        if ((((lastURI != null) && (change.getURI() != null)) && (!Objects.equal(change.getURI(), lastURI)))) {
          return false;
        }
        lastURI = change.getURI();
      }
    }
    return true;
  }
  
  @Override
  public List<EChange> getEChanges() {
    ArrayList<EChange> _arrayList = new ArrayList<EChange>();
    final Function2<ArrayList<EChange>, C, ArrayList<EChange>> _function = (ArrayList<EChange> eChangeList, C change) -> {
      eChangeList.addAll(change.getEChanges());
      return eChangeList;
    };
    return IterableExtensions.<C, ArrayList<EChange>>fold(this.changes, _arrayList, _function);
  }
  
  @Override
  public void resolveBeforeAndApplyForward(final UuidResolver uuidResolver) {
    for (final C c : this.changes) {
      c.resolveBeforeAndApplyForward(uuidResolver);
    }
  }
  
  @Override
  public void resolveAfterAndApplyBackward(final UuidResolver uuidResolver) {
    List<C> _reverseView = ListExtensions.<C>reverseView(this.changes);
    for (final C c : _reverseView) {
      c.resolveAfterAndApplyBackward(uuidResolver);
    }
  }
  
  @Override
  public Iterable<EObject> getAffectedEObjects() {
    final Function2<ArrayList<EObject>, C, ArrayList<EObject>> _function = (ArrayList<EObject> list, C element) -> {
      Iterable<EObject> _affectedEObjects = element.getAffectedEObjects();
      Iterables.<EObject>addAll(list, _affectedEObjects);
      return list;
    };
    return IterableExtensions.<EObject>filterNull(IterableExtensions.<C, ArrayList<EObject>>fold(this.changes, CollectionLiterals.<EObject>newArrayList(), _function));
  }
  
  @Override
  public Iterable<String> getAffectedEObjectIds() {
    final Function2<ArrayList<String>, C, ArrayList<String>> _function = (ArrayList<String> list, C element) -> {
      Iterable<String> _affectedEObjectIds = element.getAffectedEObjectIds();
      Iterables.<String>addAll(list, _affectedEObjectIds);
      return list;
    };
    return IterableExtensions.<String>filterNull(IterableExtensions.<C, ArrayList<String>>fold(this.changes, CollectionLiterals.<String>newArrayList(), _function));
  }
  
  @Override
  public void unresolveIfApplicable() {
    final Consumer<C> _function = (C it) -> {
      it.unresolveIfApplicable();
    };
    this.changes.forEach(_function);
  }
  
  @Override
  public Iterable<UserInteractionBase> getUserInteractions() {
    final Function1<C, Iterable<UserInteractionBase>> _function = (C it) -> {
      return it.getUserInteractions();
    };
    return Iterables.<UserInteractionBase>concat(ListExtensions.<C, Iterable<UserInteractionBase>>map(this.changes, _function));
  }
  
  @Override
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    String _simpleName = this.getClass().getSimpleName();
    _builder.append(_simpleName);
    _builder.append(", VURI: ");
    VURI _uRI = this.getURI();
    _builder.append(_uRI);
    _builder.newLineIfNotEmpty();
    {
      for(final C change : this.changes) {
        _builder.append("\t");
        _builder.append(change, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
  
  /**
   * Indicates whether some other object is "equal to" this composite change.
   * This means it is a composite change which contains the same changes as this one in no particular order.
   * @param other is the object to compare with.
   * @return true, if the object is a composite change and has the same changes in any order.
   */
  @Override
  public boolean equals(final Object other) {
    return this.isEqual(other);
  }
  
  private boolean _isEqual(final Object object) {
    return super.equals(object);
  }
  
  private boolean _isEqual(final CompositeChange<C> compositeChange) {
    int _size = this.changes.size();
    int _size_1 = compositeChange.getChanges().size();
    boolean _notEquals = (_size != _size_1);
    if (_notEquals) {
      return false;
    }
    List<C> _changes = compositeChange.getChanges();
    final LinkedList<C> remainingChanges = new LinkedList<C>(_changes);
    final Predicate<C> _function = (C it) -> {
      return this.changes.contains(it);
    };
    remainingChanges.removeIf(_function);
    return remainingChanges.isEmpty();
  }
  
  @Override
  public boolean changedEObjectEquals(final VitruviusChange change) {
    return this.isChangedEObjectEqual(change);
  }
  
  private boolean _isChangedEObjectEqual(final VitruviusChange change) {
    return super.equals(change);
  }
  
  private boolean _isChangedEObjectEqual(final CompositeChange<C> compositeChange) {
    int _size = this.changes.size();
    int _size_1 = compositeChange.getChanges().size();
    boolean _notEquals = (_size != _size_1);
    if (_notEquals) {
      return false;
    }
    List<C> _changes = compositeChange.getChanges();
    final LinkedList<C> remainingChanges = new LinkedList<C>(_changes);
    final Predicate<C> _function = (C change) -> {
      final Function1<C, Boolean> _function_1 = (C otherChange) -> {
        return Boolean.valueOf(change.changedEObjectEquals(otherChange));
      };
      return IterableExtensions.<C>exists(this.changes, _function_1);
    };
    remainingChanges.removeIf(_function);
    return remainingChanges.isEmpty();
  }
  
  private boolean isEqual(final Object compositeChange) {
    if (compositeChange instanceof CompositeChange) {
      return _isEqual((CompositeChange<C>)compositeChange);
    } else if (compositeChange != null) {
      return _isEqual(compositeChange);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(compositeChange).toString());
    }
  }
  
  private boolean isChangedEObjectEqual(final VitruviusChange compositeChange) {
    if (compositeChange instanceof CompositeChange) {
      return _isChangedEObjectEqual((CompositeChange<C>)compositeChange);
    } else if (compositeChange != null) {
      return _isChangedEObjectEqual(compositeChange);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(compositeChange).toString());
    }
  }
}
